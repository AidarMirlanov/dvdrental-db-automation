package com.digital_nomads.tst;

import com.digital_nomads.beans.*;
import com.digital_nomads.file_utils.CreateAndUpdateCSVFile;
import com.digital_nomads.random_utils.MockEntity;
import com.digital_nomads.utils.DBConnection;
import com.github.javafaker.Cat;
import org.apache.commons.math3.analysis.function.Add;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.AbstractSet;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws SQLException, IOException {
        DBConnection.openConnection("dvdrental");

       // CreateAndUpdateCSVFile.createCSVFile("Actor.csv");
        //CreateAndUpdateCSVFile.insertCSVFileInDBConnection("Insert into actor(first_name, last_name) Values(?,?)", "Actor.csv");

       // DBConnection.sqlCSV("Select * from actor", "Excel");

        //DBConnection.executeAndPrintSQLQuery("INSERT INTO actor (first_name, last_name) Values ('Kani', 'Omokeev');");
 //    DBConnection.executeAndPrintSQLQuery("SELECT * FROM actor;");
    //  DBConnection.executeAndPrintSQLQuery("SELECT first_name FROM actor WHERE actor_id = 1");
     //   DBConnection.executeAndPrintSQLQuery("SELECT * FROM address");


//        ActorBean actorBean = new ActorBean(MockEntity.generateFirstName(), MockEntity.generateLastName());
//        System.out.println(actorBean);
//        String columnName [] = {"first_name", "last_name"};
//        Object data [] = {actorBean.getFirst_name(), actorBean.getLast_name()};
//        System.out.println(DBConnection.createObject2("actor", columnName, data));

    //    DBConnection.retrieveColumnInfo("actor");

       // ActorBean.getAllActors().forEach(System.out::println);
        //System.out.println(ActorBean.getBy("last_name", "Guiness").getLast_update());

        //AddressBean.getAllAddress().forEach(System.out::println);
       // System.out.println(AddressBean.getBy("district", "California").getPostal_code());

        //CategoryBean.getAllCategory().forEach(System.out::println);
        //System.out.println(CategoryBean.getBy("name", "Action").getLast_update());

        // CityBean.getAllCity().forEach(System.out::println);
       // System.out.println(CityBean.getBy("city", "Abu Dhabi").getLast_update());

        //CountryBean.getAllCountry().forEach(System.out::println);
       // System.out.println(CountryBean.getBy("country", "Austria").getCountry_id());

       // CustomerBean.getAllCustomer().forEach(System.out::println);
       // System.out.println(CustomerBean.getBy("last_name", "Jones").getCustomer_id());

       // FilmBean.getAllFilm().forEach(System.out::println);
        //System.out.println(FilmBean.getBy("release_year", 2006).getFilm_id());
       //FilmActorBean.getAllFilmActor().forEach(System.out::println);
       // System.out.println(FilmActorBean.getBy("film_id", 140).getLast_update());

       // System.out.println(AddressBean.createAddress("Kalys-Ordo", "Atami", "Shidsuoka", 312, "9140014", "08039402545"));
       // System.out.println(AddressBean.updateAddress("Kalys-Ordo2", "Atami2", "Shidsuoka2", "0706280798", 609));
        // System.out.println(AddressBean.deleteAddress(609));

//        try {
//            DBConnection.openConnection("dvdrental");
//
//            // Создание объекта ActorBean с использованием Builder
//            ActorBean actorBean = ActorBean.builder()
//                    .first_name(MockEntity.generateFirstName())
//                    .last_name(MockEntity.generateLastName())
//                    .build();
//            System.out.println("Created ActorBean: " + actorBean);
//
//            // Вставка нового актера в базу данных
//            boolean isCreated = ActorBean.createActor(actorBean.getFirst_name(), actorBean.getLast_name());
//            System.out.println("Actor created: " + isCreated);
//
//            // Проверка, что актер был добавлен
//            ActorBean fetchedActor = ActorBean.getBy("first_name", actorBean.getFirst_name());
//            System.out.println("Fetched ActorBean: " + fetchedActor);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Закрытие соединения с базой данных
//            DBConnection.close();
//        }
        //ActorBean.getAllActors().forEach(System.out::println);
        //System.out.println(ActorBean.getBy("last_name", "Guiness").getActor_id());

        //ActorBean.printAllRecords("address");
        //ActorBean.printAllRecords("category");
        //  ActorBean.deleteRecordById("actor", "actor_id", 216);


        // ActorBean.createActor("Aidar", "Mirlanov");
//        ActorBean.createActor("Mirbek", "Atabekov");
//        System.out.println(ActorBean.createActor("Uchiha", "Itachi"));
        //ActorBean.updateActor("Naruto", "Boruto",200);
        //ActorBean.deleteActor(201);
// ActorBean actorBean = new ActorBean(MockEntity.generateFirstName(), MockEntity.generateLastName());
// String columnNames[] = {"first_name, last_name"};
// Object data [] = {actorBean.getFirst_name(), actorBean.getLast_name()  };
//        System.out.println(DBConnection.createObject("actor", columnNames, data ));

    }
    }

