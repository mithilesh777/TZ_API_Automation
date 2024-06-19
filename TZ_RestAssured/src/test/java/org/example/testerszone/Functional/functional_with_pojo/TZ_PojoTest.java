package org.example.testerszone.Functional.functional_with_pojo;

import io.restassured.parsing.Parser;
import common.util.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class TZ_PojoTest extends BaseTest {

    /**
     * serialization deserialization
     * It will convert json into java object, internally setter methods will be called to set the values.
     * then serialization will take place, that will help to get the values which is set in java object.
     * json key values from
     * @throws IOException
     */

    @Test(description = "Testing serialization deserialization",enabled = true)
    public void deserialization() throws IOException {
        System.out.println(given().expect().defaultParser(Parser.JSON).when().get(System.getProperty("user.dir")+"/src/testdata/uat/Serialization_Deserialization_Test.json").asString());
        GetDetails details = given().expect().defaultParser(Parser.JSON).when().get(System.getProperty("user.dir")+"/src/testdata/uat/Serialization_Deserialization_Test.json").as(GetDetails.class);
        String expertise = details.getExpertise();
        System.out.println(expertise);
        String instructorName = details.getInstructor();
        System.out.println(instructorName);
    }

}

