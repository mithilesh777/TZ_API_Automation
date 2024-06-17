package org.example.testerszone.Functional.functional_without_pojo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class TZ_PutTest {

    @Test(description = "put Rest Assured Api call using reqres dummy site ", priority = 1, enabled = true)
    public void updateNewUser() {
        String body = "{\n" +
                "    \"name\": \"updatedName\",\n" +
                "    \"job\": \"updatedJob\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/";
        String response =  given().log().all().header("Content-Type","application/json")
                .body(body).post("api/users/2").then()
                .assertThat().statusCode(201)
        .body("name",equalTo("updatedName")).extract().response().asString();
        System.out.println("Response is : "+response);
        JsonPath jPath = new JsonPath(response); // Parsing Json response in form of String
        String id = jPath.getString("id"); // Fetching id from parse string.
        String time =jPath.getString("createdAt");
        System.out.println("user id is : "+ id + "created time is : "+ time);
    }

}
