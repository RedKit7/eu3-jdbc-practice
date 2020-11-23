package apitests.HW;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

import static org.testng.Assert.*;


public class Q1 {




    /*

    ORDS API:
Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2


     */

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }


    @Test
    public void Q1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country_id","US")
                .when().get("/countries/{country_id}");


        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");


        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        String countryID  = jsonPath.getString("country_id");
        String countryName  = jsonPath.getString("country_name");
        String regionID  = jsonPath.getString("region_id");


        //print the values
        System.out.println("countryID = " + countryID);
        System.out.println("countryName = " + countryName);
        System.out.println("regionID = " + regionID);


        //verification
        assertEquals(countryID,"US");
        assertEquals(countryName,"United States of America");
        assertEquals(regionID,"2");

    }
}
