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


public class Q2 {




    /*

    ORDS API:
Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25

     */

    @BeforeClass
    public void beforeclass() { baseURI = ConfigurationReader.get("hr_api_url"); }


    @Test
    public void Q2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");



        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");



    //    JsonPath jsonPath = response.jsonPath();

        List <String> jopIDs = response.path("items.job_id");

        for (String jopID : jopIDs) {

            System.out.println("jopID = " + jopID);

            assertTrue(jopID.startsWith("SA"));
        }


        List <Integer> departmentIds  = response.path("items.department_id");
        for (int departmentId : departmentIds) {

            System.out.println("departmentId = " + departmentId);
            assertEquals(departmentId,80);
        }







    }


    }