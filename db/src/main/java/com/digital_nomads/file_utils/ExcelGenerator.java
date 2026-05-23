package com.digital_nomads.file_utils;
import com.digital_nomads.beans.ActorBean;
import com.digital_nomads.beans.AddressBean;
import com.digital_nomads.utils.DBConnection;
import com.opencsv.CSVWriter;
import org.apache.commons.math3.analysis.function.Add;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

    public class ExcelGenerator {

        public static void writeDataLineByLine(String filePath, ActorBean actorBean) {
            // first create file object for file placed at location
            // specified by filepath
            File file = new File(filePath);
            try {
                // create FileWriter object with file as parameter
                FileWriter outputFile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputFile);
                // adding header to csv
                Field[] fields = ActorBean.class.getDeclaredFields();
                String[] header = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    header[i] = fields[i].getName();
                }
                writer.writeNext(header);
// Adding data to csv
                String[] data = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true); // Allow access to private fields
                    Object value = fields[i].get(actorBean);
                    data[i] = value != null ? value.toString() : "";
                }
                writer.writeNext(data);

                // closing writer connection
                writer.close();
            }
            catch (IOException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public static void writeDataList(String filePath, List<ActorBean> actorBeanList) {
            // first create file object for file placed at location
            // specified by filepath
            File file = new File(filePath);
            try {
                // create FileWriter object with file as parameter
                FileWriter outputFile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputFile);
                // adding header to csv
                Field[] fields = ActorBean.class.getDeclaredFields();
                String[] header = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    header[i] = fields[i].getName();
                }
                writer.writeNext(header);
                for (ActorBean actorBean: actorBeanList) {
                    String[] data = new String[fields.length];
                    for (int i = 0; i < fields.length; i++) {
                        fields[i].setAccessible(true); // Allow access to private fields
                        Object value = fields[i].get(actorBean);
                        data[i] = value != null ? value.toString() : "";
                    }
                    writer.writeNext(data);
                }

                // closing writer connection
                writer.close();
            }
            catch (IOException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



        public static void main(String[] args) throws SQLException {

            DBConnection.openConnection("dvdrental");
//            String filePath = "/Users/aidar312/Documents/Spring2024_TAFF/db/src/main/resources/actorBean.csv";
//            writeDataList(filePath, ActorBean.getAllActors());

            String filePath = "/Users/aidar312/Documents/Spring2024_TAFF/db/src/main/resources/addressBean.csv";
            writeDataList2(filePath, AddressBean.getAllAddress());

        }

        public static void writeDataList2 (String filePath, List<AddressBean> addressBeansList) {
            // first create file object for file placed at location
            // specified by filepath
            File file = new File(filePath);
            try {
                // create FileWriter object with file as parameter
                FileWriter outputFile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputFile);
                // adding header to csv
                Field[] fields = AddressBean.class.getDeclaredFields();
                String[] header = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    header[i] = fields[i].getName();
                }
                writer.writeNext(header);
                for (AddressBean addressBean : addressBeansList) {
                    String[] data = new String[fields.length];
                    for (int i = 0; i < fields.length; i++) {
                        fields[i].setAccessible(true); // Allow access to private fields
                        Object value = fields[i].get(addressBean);
                        data[i] = value != null ? value.toString() : "";
                    }
                    writer.writeNext(data);
                }

                // closing writer connection
                writer.close();
            }
            catch (IOException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



        //Подготовить файл вручную и добавить 20 актеров и добавить этих актеров в базу данных
    }
