package gorest.Post;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreatePost {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = GoConnection.PostConnection();

        String query = "{\n" +
                "    \"user_id\": 7386820,\n" +
                "    \"title\": \"Conor is not chicken.\",\n" +
                "    \"body\": \"Мой добавленный пост Домашки fight. No way I’m going to come here without my father. It was the first time after what happened with my father, when UFC called me about Justin, I talked with my mother for three days\"\n" +
                "}";

        Response response = requestSpecification
                .body(query)
                .post("/posts");

        response.then().assertThat().statusCode(201);
        response.prettyPrint();
    }
}
