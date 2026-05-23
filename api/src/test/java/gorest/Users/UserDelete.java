package gorest.Users;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserDelete {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = GoConnection.PostConnection();

        Response response = requestSpecification
                .delete("/users/7398441");

        response.then().assertThat().statusCode(204);
        response.prettyPrint();
    }
}
