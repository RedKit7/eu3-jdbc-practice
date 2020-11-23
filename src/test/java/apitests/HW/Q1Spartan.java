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


public class Q1Spartan {

/*
    SPARTAN API
    Q1:
    Given accept type is json
    And path param id is 20
    When user sends a get request to "/api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;charset=UTF-8"
    And response header contains Date
    And Transfer-Encoding is chunked
    And response payload values match the following:
    id is 20,
    name is "Lothario",
    gender is "Male",
    phone is 7551551687


 */


    @BeforeClass
    public void beforeclass() { baseURI = ConfigurationReader.get("spartan_api_url"); }



    @Test
    public void testQ1Spartan() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("/api/spartans/{id}");


        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());


        //  If we want to verify existince of any header :
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));


        //   if you want to get any header value : -response.header("yourHearderName")
        System.out.println("response.header(\"Transfer-Encoding\") = " + response.header("Transfer-Encoding"));



        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");



        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals(response.header("Transfer-Encoding"),"chunked");


        //response.prettyPrint();
        //printing each key value in the json body/payload

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.body().path("gender").toString());
        System.out.println(response.body().path("phone").toString());

        //save json key values
        int id =  response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert one by one
        assertEquals(id,20);
        assertEquals(name,"Lothario");
        assertEquals(gender,"Male");
        assertEquals(phone,7551551687l);





        }
    }