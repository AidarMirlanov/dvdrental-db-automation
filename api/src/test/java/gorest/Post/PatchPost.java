package gorest.Post;

import demo.GoConnection;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchPost {
    public static void main(String[] args) {

        RequestSpecification requestSpecification = GoConnection.PostConnection();

        String query = "{\n" +
                "        \"body\": \"Double champ fight. No way I’m going to come here without my father. It was the first time after what happened with my father, when UFC called me about Justin, I talked with my mother for three days\"\n" +
                "    }";

        Response response = requestSpecification
                .body(query)
                .patch("/posts/155113");

        response.then().assertThat().statusCode(200);
        //response.prettyPrint()
    }
}
