package gorest.Post;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllPost {
    public static void main(String[] args) {

        RequestSpecification requestSpecification = GoConnection.PostConnection();

        Response response = requestSpecification
                .get("/posts ");
        response.prettyPrint();
    }
}
