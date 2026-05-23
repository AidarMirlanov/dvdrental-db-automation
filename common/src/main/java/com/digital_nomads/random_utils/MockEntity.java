package com.digital_nomads.random_utils;

import com.github.javafaker.Faker;

import java.sql.Timestamp;

public class MockEntity {
    static Faker faker = new Faker();
    public static String generateFirstName () {
        return faker.name().firstName();

    }

    public static String generateLastName () {
        return faker.name().lastName();

    }
    public static void main(String[] args) {
        System.out.println(generateFirstName());
    }

}
