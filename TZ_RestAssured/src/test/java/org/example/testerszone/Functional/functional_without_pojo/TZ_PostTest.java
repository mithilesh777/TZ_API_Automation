package org.example.testerszone.Functional.functional_without_pojo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import common.util.BaseTest;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

public class TZ_PostTest extends BaseTest {

    @Test(dataProvider = "common", description = "post Rest Assured Api call using reqres dummy site ", priority = 1, enabled = true)
    public void createNewUser(LinkedHashMap<String ,String> obj) {
        HashMap<String, String> body = new HashMap<>();
        body.put("name",Math.random()+obj.get("name"));
        body.put("job",Math.random()+obj.get("job"));

        RestAssured.baseURI = "https://reqres.in/";
        String response =  given().log().all().header("Content-Type","application/json")
                .body(body).post("api/users").then().assertThat().statusCode(201).extract().response().asString();
        System.out.println("Response is : "+response);
        JsonPath jPath = new JsonPath(response); // Parsing Json response in form of String
        String id = jPath.getString("id"); // Fetching id from parse string.
        String time =jPath.getString("createdAt");
        System.out.println("user id is : "+ id + "created time is : "+ time);
    }
}
