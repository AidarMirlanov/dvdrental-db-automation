package com.digital_nomads.beans;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.*;

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


public class ActorBean {

    int actor_id;
    String first_name;
    String last_name;
    Timestamp last_update;

    public ActorBean(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;

    }

    public static List<ActorBean> getAllActors() throws SQLException {
        String query = "select * from public.actor;";
        try (ResultSet resultSet = query(query)) {
            return new BeanProcessor().toBeanList(resultSet, ActorBean.class);
        }
    }

    public static ActorBean getBy(String column, String value) throws SQLException {
        String query = "select * from public.actor where " + column + " = ?;";
        ResultSet resultSet = query(query, value);
        if (!resultSet.next()) {
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, ActorBean.class);
        }
    }


    public static boolean createActor(String first_name, String last_name) throws SQLException {
        String query = "Insert into actor (first_name, last_name, last_update)" +
                "Values (?, ?, CURRENT_TIMESTAMP  )";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }

    }

    public static boolean updateActor(String first_name, String last_name, int actor_id) throws SQLException {
        String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setObject(3, actor_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static boolean deleteActor(int actor_id) throws SQLException {
        String query = "DELETE FROM actor WHERE actor_id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, actor_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static ResultSet getAllRecords(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName + ";";
        return query(query);


    }

//    public static  ActorBean generateActor() {
//        return ActorBean.builder()
//                .first_name(MockEntity.generateLastName())
//                .last_name(MockEntity.generateLastName()).build();

    }

//    public static void main(String[] args) {
//        ActorBean actorBean = ActorBean.generateActor();
//        System.out.println(actorBean);









