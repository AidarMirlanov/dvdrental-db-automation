package pojoUser;

import io.restassured.RestAssured;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import java.util.List;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;

public class UserSteps {
    private final static String token = "7103aef3b3f57d859fa689907685e8dd3a8ee4406a15ac61ec7a5b19bd535d9d";

    public static ResponseUser createUser () {
        Specification.installSpecification(Specification.requestSpecification(), Specification.responseSecUser201());
        RequestUser requestUser = RequestUser.builder()
                .name(UserGenerate.generateName())
                .email(UserGenerate.generateEmail())
                .gender(UserGenerate.generateGender())
                .status(UserGenerate.generateStatus())
                .build();


        ResponseUser responseUser = given()
                .auth().oauth2(token)
                 .body(requestUser)
                .post("/users")
                .then()
                .extract().response().as(ResponseUser.class);
        return responseUser;


    }

    public static ResponseUser updateUser () {
        Specification.installSpecification(Specification.requestSpecification(), Specification.responseSecUser200());
        RequestUser requestUser = RequestUser.builder()
                .name(UserGenerate.generateName())
                .email(UserGenerate.generateEmail())
                .gender(UserGenerate.generateGender())
                .status(UserGenerate.generateStatus())
                .build();


        ResponseUser responseUser = given()
                .auth().oauth2(token)
                .body(requestUser)
                .put("/users/7400482")
                .then()
                .extract().response().as(ResponseUser.class);
        return responseUser;
    }

    public static List<ResponseUser> getAllUsers () {
        Specification.installSpecification(Specification.requestSpecification(), Specification.responseSecUser200());
        List<ResponseUser> users = given()
                .auth()
                .oauth2(token)
                .get("/users")
                .then()
                .extract().body().jsonPath().getList( "data", ResponseUser.class);
        return users;

    }
}
