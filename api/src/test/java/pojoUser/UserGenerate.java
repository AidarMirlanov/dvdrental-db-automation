package pojoUser;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserGenerate {
    static Faker faker = new Faker();

    public static String generateName () {
        return faker.name().name();
    }

    public static String generateEmail () {
        return faker.internet().emailAddress();
    }
    public static String generateGender () {
        String [] genders = {"male", "female"};
        return genders[faker.number().numberBetween(0, genders.length)];
    }
    public static String generateStatus () {
        String [] status = {"active", "inactive"};
        return status[faker.number().numberBetween(0, status.length)];

    }



}
