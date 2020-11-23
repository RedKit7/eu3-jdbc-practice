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


public class Q3 {




    /*

    ORDS API:
Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */

    @BeforeClass
    public void beforeclass() { baseURI = ConfigurationReader.get("hr_api_url"); }


    @Test
    public void getCountriesWithPath() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\").toString() = " + response.path("hasMore").toString());


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
       // assertEquals(response.path("hasMore"),"false");



        List <Integer> regionIds  = response.path("items.region_id");

        for (int regionId : regionIds) {

            System.out.println("regionId = " + regionId);
            assertEquals(regionId,3);
        }


        List <String> countryNames = response.path("items.country_name");

        for (String countryName : countryNames) {

            System.out.println("countryName = " + countryName);

            assertTrue(response.body().asString().contains("Australia"));
            assertTrue(response.body().asString().contains("China"));
        }




    }


}