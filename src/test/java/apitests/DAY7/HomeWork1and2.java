package apitests.DAY7;

import apitests.DAY6_POJO.Spartan;

import io.restassured.http.ContentType;

import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomeWork1and2 {
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void homew1() {
        //Optional Homeworks
        //HomeWokr 1
        //1-Createcsv file from mackaroo website , which inclides name,gender,pphone
        //2- Download csv file
        //3- using testng data provider and apache poi create data driven posting from spartan

        //HomeWork2
        //create one mackaroo api for name , gender.phone
        //Send get request to retrieve rondom info from that api
        //use those info to send post request to spartan


        Spartan spartan = new Spartan();
        spartan.setName("MikeE4");
        spartan.setGender("Male");
        spartan.setPhone(8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartan)
                .when().post("/api/spartans").then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .and()
                .body("success", is("A Spartan is Born!"),
                        "data.name", equalTo("MikeEU3"),
                        "data.gender", equalTo("Male"),
                        "data.phone", is(8877445596l));


    }
}