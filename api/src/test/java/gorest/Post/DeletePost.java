package gorest.Post;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeletePost {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = GoConnection.PostConnection();
        Response response = requestSpecification
                .delete("/posts/155112");

        response.then().assertThat().statusCode(204);
        response.prettyPrint();
    }
}
