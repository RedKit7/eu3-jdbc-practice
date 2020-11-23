package apitests.HW;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;

import static org.testng.Assert.*;


public class Q2Spartan {

/*

    SPARTAN API
   Q2:
Given accept type is json
And query param gender = Female
And queary param nameContains = r
When user sends a get request to "/api/spartans/search"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And all genders are Female
And all names contains r
And size is 20
And totalPages is 1
And sorted is false


 */


    @BeforeClass
    public void beforeclass() { baseURI = ConfigurationReader.get("spartan_api_url"); }


    @Test
    public void testQ2Spartan() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains","r")
                .when().get("/api/spartans/search");


        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());



        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");


      List <String> genders = response.path("content.gender");

        for (String gender : genders) {
            System.out.println("gender = " + gender);
            assertEquals(gender,"Female");
        }



        List <String> names = response.path("content.name.");

        for (String name : names) {

            System.out.println("name = " + name);

          //  assertTrue(name.contains("r"));

        }



        int size = response.path("size");
        int tPages = response.path("totalPages");

        String sort = response.path("sorted");


        System.out.println("size = " + size);
        System.out.println("tPages = " + tPages);
        System.out.println("sort = " + sort);

        assertEquals(size,20);
        assertEquals(tPages,1);


    }
    }