package apitests.DAY9;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BokItAuthTest {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("book_it");
    }

    String accesToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.3YSCwcTXRcEygBm7LvBLb6_D8jU5WXjAD6E3VA9oh0o";

    @Test
    public void getAllCampuses(){
        Response response = given().header("Authorization",accesToken)
                .when().get("api/campuses");

        response.prettyPrint();
        System.out.println(response.statusCode());
    }
}