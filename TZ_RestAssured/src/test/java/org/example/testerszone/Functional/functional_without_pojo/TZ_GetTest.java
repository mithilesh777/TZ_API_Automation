package org.example.testerszone.Functional.functional_without_pojo;

import common.util.TestInfo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import common.util.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TZ_GetTest extends BaseTest {

    // given --> Pre-requisites/input details
    // when --> Action
    // Then --> validate output

    @Test(description = "get list of users using reqres dummy site ", priority = 1, enabled = false)
    @TestInfo(id = "001",description = "Get List of all the users using get http method.")
    public void getListOfUsers() {

        //https://reqres.in/api/users?page=2
        RestAssured.baseURI = "https://reqres.in/";
        String response =  given().log().all().queryParam("page","2")
                .get("api/users").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println("Response is : "+response);
        JsonPath jPath = new JsonPath(response);
        String email = jPath.getString("data.email");
        System.out.println("email id : "+ email);
    }

    @Test(description = "get single user detail using reqres dummy site",priority = 2)
    public void getSingleUser() {
        //https://reqres.in/api/users/2
        RestAssured.baseURI = "https://reqres.in/";
        //Approach 1: without using path param.

        String response =  given().log().all().header("Content-Type","application/json")
                .get("api/users/2").then().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Response is : "+response);
        JsonPath jPath = new JsonPath(response);
        String email = jPath.getString("data.email");
        System.out.println("email id : "+ email);
        Assert.assertTrue(email.equalsIgnoreCase("janet.weaver@reqres.in"),"email id did not match");


        //Approach 2: with using path param
        //api/users/{pageNo} --> {pageNo} is acting as variable.

        String response1 =  given().log().all().header("Content-Type","application/json").pathParam("pageNo","2")
                .get("api/users/{pageNo}").then().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Response is : "+response1);
        JsonPath jPath1 = new JsonPath(response1);
        String email1 = jPath1.getString("data.email");
        System.out.println("email id : "+ email1);
        Assert.assertTrue(email1.equalsIgnoreCase("janet.weaver@reqres.in"),"email id did not match");
    }

    @Test(description = "404 response testing",priority = 2)
    public void get404Response() {
        RestAssured.baseURI = "https://reqres.in/";
        String response =  given().log().all().header("Content-Type","application/json")
                .get("api/users/23").then().assertThat().statusCode(404).extract().response().asString();
        System.out.println("Response is : "+response);
    }
}
