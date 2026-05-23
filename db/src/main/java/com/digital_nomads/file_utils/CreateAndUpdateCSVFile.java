package com.digital_nomads.file_utils;

import com.digital_nomads.random_utils.MockEntity;
import com.digital_nomads.utils.DBConnection;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAndUpdateCSVFile {

    //метод для создания CSV файл
    public static void createCSVFile (String fileName) throws IOException {
        File file = new File(fileName);
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);
            String [] header = {"first_name", "last_name"};
            writer.writeNext(header);
            for (int i = 0; i<20; i++) {
                String [] randomNames = {MockEntity.generateFirstName(), MockEntity.generateLastName()};
                writer.writeNext(randomNames);
            }
//            String [] bradPitt = {"Brad", "Pitt"};
//            writer.writeNext(bradPitt);
//            String [] angelinaJolie = {"Angelina", "Jolie"};
//            writer.writeNext(angelinaJolie);
            writer.close();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    //Метод для добавления csvFile в БД
    public static void insertCSVFileInDBConnection (String query, String filepath) throws IOException, SQLException {
        try (CSVReader reader = new CSVReader(new FileReader(filepath));
             PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
                 reader.readNext();
                 String [] nextLine;
                 while ((nextLine = reader.readNext()) != null) {
                     preparedStatement.setString(1, nextLine[0]);
                     preparedStatement.setString(2, nextLine[1]);

                     preparedStatement.addBatch();
                 }
            preparedStatement.executeBatch();
        }
    }

    public static void universalMethod (String query, String fileName) throws IOException, SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTimeInfo = dateFormat.format(new Date());
        fileName.concat(String.format("_&s", dateTimeInfo));
        FileWriter fileWriter = new FileWriter(fileName + ".csv");
        ResultSet resultSet = DBConnection.query(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 0; i < columnCount; i++ ) {
            fileWriter.append(resultSet.getMetaData().getColumnLabel(i));
            if (i < columnCount) {
                fileWriter.append(",");
            } else {
                fileWriter.append("\n");
            }
        }
            while (resultSet.next()) {
                for ( int i = 0; i<columnCount; i++) {
                    fileWriter.append(resultSet.getString(i));
                    if (i<columnCount) {
                        fileWriter.append(",");
                    }
                }
            }
            fileWriter.flush();
            fileWriter.close();
        }
    }
