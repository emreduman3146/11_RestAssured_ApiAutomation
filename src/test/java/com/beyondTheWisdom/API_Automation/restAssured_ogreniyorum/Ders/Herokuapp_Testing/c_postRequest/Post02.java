package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;


import static io.restassured.RestAssured.given;

public class Post02 extends TestBase {

      /*
    POST SCENARIO:
    AcceptType : json
    when I post a request
    "https://restful-booker.herokuapp.com/"
    request body:

         {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 383,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-11-30",
                "checkout": "2019-09-11"
            },
            "additionalneeds":"Wifi"
        }

        Then status code:200
        response body should match with request body

     */

    @Test
    public void jsonDatamizi_JSONObject_classini_kullanarak_olusturalim__AND__postRequest_atalim()
    {


        /*
          json obesi olusturmamıza yarar JSONObject Class'ı
          for instance:
               "bookingdates": {
                        "checkin": "2018-11-30",
                        "checkout": "2019-09-11"
                      }
          bu datayı bir json object yapmak istiyorum
         */

        //JSON DATASI OLUSTURAN CLASSTIR.
        JSONObject jsonBookingdatesObject=new JSONObject();
        jsonBookingdatesObject.put("checkin","2018-11-30");
        jsonBookingdatesObject.put("checkout","2019-09-11");

        System.out.println(jsonBookingdatesObject);
        //{"checkin":"2018-11-30","checkout":"2019-09-11"}


        JSONObject jsonRequestBody=new JSONObject();
        jsonRequestBody.put("firstname", "Sally");
        jsonRequestBody.put("lastname", "Brown");
        jsonRequestBody.put("totalprice", "383");
        jsonRequestBody.put("depositpaid", "true");
        jsonRequestBody.put("bookingdates",jsonBookingdatesObject);//composition,aggreagtion,asssociation
        jsonRequestBody.put("additionalneeds","Wifi");


        System.out.println(jsonRequestBody);

        requestSpecification01.basePath("/booking");

        Response response= given().
                            contentType(ContentType.JSON).
                            spec(requestSpecification01).
                            body(jsonRequestBody.toString())//compile-time hatasi vermez // run-time hatasi alabiliriz
                         .when()
                           .post();

        response.prettyPrint();

        response.
            then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON);


        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        //verify datas
        softAssert.assertEquals(jsonPath.getString("booking.firstname"),"Sally");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),"Brown");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),383);
        softAssert.assertEquals(jsonPath.getString("booking.totalprice"),"383");//üstteki ile aynı isi görür
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),"2018-11-30");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),"2019-09-11");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),"Wifi");

        softAssert.assertAll();



    }


    @Test
    public void stringToJsonObject_yapalim__AND__postRequest_atalim__AND__extract_path_ile_id_alip_getRequestAtalim()
    {
        String jsonRequestBody="{\n" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Brown\",\n" +
                "\"totalprice\": 383,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2018-11-30\",\n" +
                "\"checkout\": \"2019-09-11\"\n" +
                "},\n" +
                "\"additionalneeds\":\"Wifi\"\n" +
                "}";

        //STRING TO JSONOBJECT
        JSONObject jsonObject = new JSONObject(jsonRequestBody);

        System.out.println(jsonObject);

        requestSpecification01.basePath("/booking");

        Response response=
            given().
                contentType(ContentType.JSON).
                spec(requestSpecification01).
                body(jsonObject.toString())
            .when()
                .post();

        response.prettyPrint();



        //ASSERTION
        String bookingID=response.
                then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                extract().
                    response().path("bookingid").toString();


        //get request --> baseURL/booking/4845


        //requestSpecification--> REQUEST ATARKEN(BODY-PARAMS-HEADERS-ENDPOINTS)
        requestSpecification01.pathParam("bookingid",bookingID);  // BASEURL/BASEPATH/PATHPARAM


        //GET REQUEST
        given().
                spec(requestSpecification01).
        when().
            get("/{bookingid}").prettyPrint();




    }



    @Test
    public void getJSONData_fromFile__AND__postRequest_atalim()
    {


        // Read JSON data from file
        File jsonFile = new File("src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/Herokuapp_Testing/c_postRequest/herokuapp_json_post.json");

        requestSpecification01.basePath("/booking");

        Response response= given().
            contentType(ContentType.JSON).
            spec(requestSpecification01).
            body(jsonFile)
            .when()
            .post();

        response.prettyPrint();

        String bookingID=response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).extract().response().path("bookingid").toString();



        requestSpecification01.pathParam("bookingid",bookingID);

        given().
                spec(requestSpecification01).
                get("/{bookingid}").prettyPrint();



    }


}
