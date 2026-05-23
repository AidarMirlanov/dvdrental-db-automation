package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.requestSpecification;

public class GoConnection {

    public static RequestSpecification PostConnection () {
        return  RestAssured.given()
                .baseUri("https://gorest.co.in/public/v2")
                .header("Authorization", "Bearer 34951f9efb376fbab46435edb35a6580fc7b7a7a38f4138e2ab56db6fe67e045")
                .header("Content-Type", "application/json");
    }
}
