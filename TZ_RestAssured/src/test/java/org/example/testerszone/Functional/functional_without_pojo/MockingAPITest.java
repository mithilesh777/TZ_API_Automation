package org.example.testerszone.Functional.functional_without_pojo;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MockingAPITest {

/**
 1. Print No of courses returned by API
 2. Print Purchase Amount
 3. Print Title of the first course
 4. Print All course titles and their respective Prices
 5. Print no of copies sold by RPA Course
 6. Verify if Sum of all Course prices matches with Purchase Amount


 */

/*
Mock API we use whenever API is not developed, we can ask for sample response to develop the test script
later once API is developed we can integrate with our script.
 */
    @Test(description = "Testing mocking API",enabled = true)
    public void mockAPI() throws IOException {
        String mockResponse = "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 760,\n" +
                "\n" +
                "\"website\": \"testerszone.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium java\",\n" +
                "\n" +
                "\"price\":50,\n" +
                "\n" +
                "\"copies\":6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\":40,\n" +
                "\n" +
                "\"copies\":4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\":30,\n" +
                "\n" +
                "\"copies\":10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";
        //way 1: using String variable in class level;
          //JsonPath jPath = new JsonPath(mockResponse);

        //way 2: using json file
          JsonPath jPath = new JsonPath(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/testdata/uat/MockingAPITest.json"))));

        //Either use Way 1 or Way 2 and then use common code.

        int totalActualPurchaseAmount = jPath.getInt("dashboard.purchaseAmount");
        String[] noOfCoursesOffered = jPath.getString("courses.title").split(",");
        System.out.println("Offered courses no are: "+noOfCoursesOffered.length);
        for(int i=0;i<=noOfCoursesOffered.length-1;i++) {
            String courseName = noOfCoursesOffered[i].replace("[","").replace("]","").trim();
            System.out.print(courseName+"\n");
        }
        System.out.println();
        String[] priceCountAsPerCourse = jPath.getString("courses.price").split(",");
        String[] copiesOfCourses = jPath.getString("courses.copies").split(",");
        int totalExpectedPurchaseAmount = 0;
        for(int i =0; i<=priceCountAsPerCourse.length-1; i++) {
       //Approach 1
//            String price = priceCountAsPerCourse[i].trim().replace("[","").replace("]","");
//            String copies = copiesOfCourses[i].trim().replace("[","").replace("]","");
//            totalExpectedPurchaseAmount=  Integer.valueOf(price)*Integer.valueOf(copies)+totalExpectedPurchaseAmount;
//
            //Approach 2
            int price1 = jPath.getInt("courses["+i+"].price");
            int copies1 = jPath.getInt("courses["+i+"].copies");
            totalExpectedPurchaseAmount=  price1*copies1+totalExpectedPurchaseAmount;

        }
        System.out.println("Actual Price : "+totalActualPurchaseAmount);
        System.out.println("Expected Price : "+totalExpectedPurchaseAmount);
        Assert.assertEquals(totalActualPurchaseAmount,totalExpectedPurchaseAmount,"Actual price is: "+totalActualPurchaseAmount +"Expected price is: "+totalExpectedPurchaseAmount );

    }
}
