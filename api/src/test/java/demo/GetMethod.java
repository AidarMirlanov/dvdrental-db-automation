package demo;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetMethod {
    public static void main(String[] args) {
//
        RequestSpecification requestSpecification = GoConnection.PostConnection();
        Response response = requestSpecification
                .get("/users");
        response.prettyPrint();



//        RequestSpecification requestSpecification = RestAssured.given();
//        String userPayLoad = "{\n" +
//                "        \"name\": \"Gaidar Gaidarovich\",\n" +
//                "        \"email\": \"Gaidar13@example.com\",\n" +
//                "        \"gender\": \"male\",\n" +
//                "        \"status\": \"active\"\n" +
//                "    }";
//
//        RequestSpecification rs = requestSpecification
//                .baseUri("https://gorest.co.in/public/v2")
//                .header("Authorization", "Bearer 34951f9efb376fbab46435edb35a6580fc7b7a7a38f4138e2ab56db6fe67e045")
//                .header("Content-Type", "application/json")
//                .body(userPayLoad);
//
//        Response response = requestSpecification
//                .post("/users");
//
//        response.then().assertThat().statusCode(201);
//        response.prettyPrint();


//        RequestSpecification requestSpecification = GoConnection.PostConnection();
//        Response response = requestSpecification
//                .get("/users");
//        response.prettyPrint();



//        RequestSpecification requestSpecification =GoConnection.PostConnection();
//
//        String userPayLoad = "{\n" +
//                "        \"name\": \"Alex Volkonovski\",\n" +
//                "        \"email\": \"Alex555@example.com\",\n" +
//                "        \"gender\": \"male\",\n" +
//                "        \"status\": \"active\"\n" +
//                "    }";
//
//        Response response = requestSpecification
//                .body(userPayLoad)
//                .post("/users");
//
//        response.then().assertThat().statusCode(201);
//        response.prettyPrint();


    }
}
