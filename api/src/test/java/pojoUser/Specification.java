package pojoUser;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpecification () {
        return new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
    public static ResponseSpecification responseSecUser201 () {
        return new  ResponseSpecBuilder()
                .expectStatusCode(201)
                .log(LogDetail.ALL)
                .build();
    }

    public static void installSpecification (RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
    public static ResponseSpecification responseSecUser200 () {
        return new  ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();
    }
    public static ResponseSpecification responseSecUser404 () {
        return new  ResponseSpecBuilder()
                .expectStatusCode(404)
                .log(LogDetail.ALL)
                .build();
    }
    public static ResponseSpecification responseSecUser204 () {
        return new  ResponseSpecBuilder()
                .expectStatusCode(204)
                .log(LogDetail.ALL)
                .build();
    }
}
