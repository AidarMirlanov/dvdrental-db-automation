package com.digital_nomads.utils;

import com.digital_nomads.config_reader.DBConfigReader;
import org.postgresql.ds.PGSimpleDataSource;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DBConnection {

    //Класс для соединения с базами данных SQL

    private static Connection connection; // отвечает за соед с базами данных
    private static Statement statement; // для выполнения sql запросов
    private static Logger log = Logger.getLogger(DBConnection.class.getName());


    //Singleton pattern Одиночка
    //Конструктор класса объявляется как private, чтобы предотвратить создание объекта с помощью оператора
    // new за пределами класса. В классе создается статическое поле, содержащее единственный экземпляр
    // этого класса, и статический метод для получения этого экземпляра.
    private DBConnection() {


    }

    private static PGSimpleDataSource getBaseDateSource(String database) {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource() {{
            setServerName(DBConfigReader.getValue("server"));
            setPortNumber(Integer.parseInt(DBConfigReader.getValue("port")));
            setUser(DBConfigReader.getValue("user"));
            setPassword(DBConfigReader.getValue("password"));
            setDatabaseName(database);
        }};

        return pgSimpleDataSource;
    }

    public static void openConnection(String database) throws SQLException {
        if (connection == null) {
            connection = getBaseDateSource(database).getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
    }

    // String query это  select * from Customers
    // Object ... params это  select * from customers where city = "london";
    public static ResultSet query(String query, Object... params) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Соединение с базой данных не установлено. Вызовите метод openConnection сначала.");
        }
        if (params.length == 0) {
            //интерфейс statement работает только с обычными запросами, не работает с параметрами запросами
            return statement.executeQuery(query);
        } else {
            //PreparedStatement работает с параметр. запросами
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        }

    }

    //Универсальные методы который будет создавать, удалять и обновлять.
    public static void createObject(String tableName, String columns, String values) throws SQLException {
        String query = "INSERT INTO " + tableName + "(" + columns + ") VALUES (" + values + ")";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }

    }

    public static void updateObject(String tableName, String setClause, String whereClause) throws SQLException {
        String query = " UPDATE FROM " + tableName + "SET" + setClause + "WHERE " + whereClause;
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteObject(String tableName, String whereClause) throws SQLException {
        String query = " DELETE FROM " + tableName + "where " + whereClause;
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }


    public static boolean createObject2(String tableName, String[] columns, Object[] values) throws SQLException {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Number of columns and values must match");
        }
        StringBuilder columnNames = new StringBuilder();
        StringBuilder placeHolder = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {
            columnNames.append(columns[i]);
            placeHolder.append("?");
            if (i < columns.length - 1) {
                columnNames.append(", ");
                placeHolder.append(", ");

            }

        }

        String query = String.format("INSERT INTO %s (%s) values (%s);", tableName, columnNames, placeHolder);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);

            }
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }

    }

    //Проверят название столбов
    public static void retrieveColumnInfo(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE actor_id = 176";
        ResultSet resultSet = DBConnection.query(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                String columnValue = resultSet.getString(i);
                System.out.println("Имя столбца: " + columnName + " Значение: " + columnValue);
            }
        }
    }

    public static void retrieveColumnInfo2(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        ResultSet resultSet = DBConnection.query(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                String columnValue = resultSet.getString(i);
                System.out.println("Имя столбца: " + columnName + " Значение: " + columnValue);
            }
        }
    }

    //Это универсальный метод для всего в SQL
    public static void executeAndPrintSQLQuery(String sqlQuery) throws SQLException {
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sqlQuery)) {
            boolean hasResult = preparedStatement.execute();
            if (hasResult) {
                ResultSet resultSet = preparedStatement.getResultSet();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(resultSetMetaData.getColumnName(i) + "\t");
                }
                System.out.println();
                while (resultSet.next()) {
                    for (int i = 1; i < columnCount; i++) {
                        System.out.println(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } else {
                int rowsAffected = preparedStatement.executeUpdate();
            }
        }
    }

    // метод для извлечения с БД для excel итп
    public static void sqlCSV (String query, String baseFileName) {
        log.info("Creating CSVFile : " + baseFileName);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = baseFileName + "_" + timeStamp;
        try {
            FileWriter fw = new FileWriter(fileName + ".csv");
            if (connection.isClosed()) {
                statement = connection.createStatement();
            }
            ResultSet resultSet = statement.executeQuery(query);
            int ColumnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i < ColumnCount; i++) {
                fw.append(escapeCSV(resultSet.getMetaData().getColumnLabel(i)));
                if (i < ColumnCount) {
                    fw.append(',');
                } else {
                    fw.append('\n');
                }
            }
            while (resultSet.next()) {
                for (int i =1; i<= ColumnCount; i++ ) {
                    fw.append(escapeCSV(resultSet.getString(i)));
                    if (i< ColumnCount) {
                        fw.append(',');
                    }else {
                        fw.append('\n');
                    }
                }
            }
            fw.flush();
            fw.close();
            log.info("csv file is created successfully");
            connection.close();
        }
        catch (Exception e) {
            log.severe("Error while closing connection" + e.getMessage());
        }
    }


    public static String escapeCSV (String values) {
        if (values == null) {
            return "";
        } else if (values.contains(";") || values.contains("\"") || values.contains("\"")) {
            values = "\"" + values.replace("\"", "\"\"") + "\"";
        }
        return values;
    }


    public static void close() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }


}
