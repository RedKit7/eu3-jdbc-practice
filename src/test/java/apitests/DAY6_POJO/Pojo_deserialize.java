package apitests.DAY6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://54.165.32.99:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //JSON to POJO --> de-serialize to java custom class
        //JSON to Our Spartan class object

        Spartan spartan15 = response.body().as(Spartan.class); // if you have added the gson , jackson library to your poom.xml this "as" method
        // will convert your result to a Spartan object

        System.out.println(spartan15);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //assertion
        assertEquals(spartan15.getId(),15);
        assertEquals(spartan15.getName(),"Meta");

    }

    @Test
    public void regionToPojo(){

        Response response = when().get("a");

        assertEquals(response.statusCode(),200);

        //JSON to POJO(regions class)

        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionId());
    }


    @Test
    public void gson_example(){

        Gson gson = new Gson();

        //JSON to JAva collections or Pojo --> De-serialization

        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);


        //-----------SERIALIZATION---------------
        //converting custom class to Json
        //JAVA Collection or POJO to JSON

        Spartan spartanEU = new Spartan(200,"Mike","Male",123123123);

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);

    }


}