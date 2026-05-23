package com.digital_nomads.beans;

import com.digital_nomads.utils.DBConnection;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.digital_nomads.utils.DBConnection.getConnection;
import static com.digital_nomads.utils.DBConnection.query;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class AddressBean {
    int address_id;
    String address;
    String address2;
    String district;
    int city_id;
    String postal_code;
    String phone;
    Timestamp last_update;

    public static List<AddressBean> getAllAddress() throws SQLException {
        String query = "select * from public.address;";
        try (ResultSet resultSet = query(query)) {
            return new BeanProcessor().toBeanList(resultSet, AddressBean.class);
        }
    }

    public static AddressBean getBy(String column, String value) throws SQLException {
        String query = "select * from public.address where " + column + " = ?;";
        ResultSet resultSet = query(query, value);
        if (!resultSet.next()) {
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, AddressBean.class);
        }
    }

    public static boolean createAddress(String address, String address2, String district, int city_id, String postal_code,
                                        String phone) throws SQLException {
        String query = "Insert into address (address, address2, district, city_id, postal_code, phone, last_update )" +
                "Values (?, ?, ? , ?, ?, ?, CURRENT_TIMESTAMP  )";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, address2);
            preparedStatement.setString(3, district);
            preparedStatement.setInt(4, city_id);
            preparedStatement.setString(5, postal_code);
            preparedStatement.setString(6, phone);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }

    }

    public static boolean updateAddress(String address, String address2, String district, String phone, int address_id) throws SQLException {
        String query = "UPDATE address SET address = ?, address2 = ?, district = ?, phone = ? WHERE address_id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, address2);
            preparedStatement.setObject(3, district);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, address_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static boolean deleteAddress(int address_id) throws SQLException {
        String query = "DELETE FROM address WHERE address_id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, address_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
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

}








