package gorest;

import demo.GoConnection;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.impl.auth.GGSSchemeBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GorestTests {

    @Test (description = "Get all users test")
    public void getAllUsersTest () {
        RequestSpecification requestSpecification = GoConnection.PostConnection();
//        RequestSpecification requestSpecification = RestAssured.given()
//                .baseUri("https://gorest.co.in/public/v2")
//                .header("Authorization", "Bearer 34951f9efb376fbab46435edb35a6580fc7b7a7a38f4138e2ab56db6fe67e045")
//                .header("Content-Type", "application/json");

         Response response = requestSpecification.get("/users");

       JsonPath jsonPath = response.jsonPath();

        String userName = jsonPath.getString("[0].name");
        System.out.println(userName);
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test(description = "Get a user test")
    public void getAllUserTest () {
        RequestSpecification requestSpecification = GoConnection.PostConnection();

        Response response = requestSpecification.get("/users/7398128");

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        Assert.assertEquals(id, 7398128);

        String name = jsonPath.getString("name");
        Assert.assertEquals(name, "Askar Akaev");

        String email = jsonPath.getString("email");
        Assert.assertEquals(email, "firstpresedent@braun.example");

        String gender = jsonPath.getString("gender");
        Assert.assertEquals(gender, "male");

        String status = jsonPath.getString("status");
        Assert.assertEquals(status, "active");
        response.prettyPrint();
        response.then().statusCode(200);


    }

    @Test (description = "Put a user ")
            public void putAUser () {
        RequestSpecification requestSpecification = GoConnection.PostConnection();

        Response response = requestSpecification.put("/users/7398466");

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        Assert.assertEquals(id, 7398466);
        String name = jsonPath.getString("name");
        Assert.assertEquals(name, "Alex update DAGESTAN");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
