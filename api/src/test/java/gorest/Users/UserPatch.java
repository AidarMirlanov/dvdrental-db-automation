package gorest.Users;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserPatch {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = GoConnection.PostConnection();

        String query = "{\n" +
                "    \n" +
                "        \"name\": \"Alex DAGESTAN\"\n" +
                "        \n" +
                "    }";

        Response response = requestSpecification
                .body(query)
                .patch("/users/7398466");
response.then().assertThat().statusCode(200);

    }
}
