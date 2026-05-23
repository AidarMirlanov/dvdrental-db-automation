package com.digital_nomads.config_reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfigReader {

    private static Properties properties;

    private DBConfigReader () {
    }


    static {
        try {
            String path = "/Users/aidar312/Documents/Spring2024_TAFF/db/src/main/resources/dbconnection.properties";
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getValue (String key) {
        return properties.getProperty(key).trim();
    }

    public static void main(String[] args) {
        System.out.println(getValue("user"));
    }
}
