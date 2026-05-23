package gorest.Users;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserPut {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = GoConnection.PostConnection();

        String query = "{\n" +
                "        \"name\": \"Alex update DAGESTAN\",\n" +
                "        \"email\": \"Alex666@example.com\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"status\": \"active\"\n" +
                "    }";

        Response response = requestSpecification
                .body(query)
                .put("/users/7398466");

        response.then().assertThat().statusCode(200);
    }
}
