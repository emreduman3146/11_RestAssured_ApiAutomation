package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request01;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Put01 extends TestBase
{


    //NORMALDE, ILGILI ENDPOINT(DB'DEKI SQL TABLELAR) UZERINDE API FUNTIONAL TESTING YAPILACAKSA
    //SIRALAMA SU SEKILDE OLMALIDIR
    //PING + POST + GET + PUT + PATCH(Sadece belli bir datayi yamalama) + DELETE
    @Test
    public void kullaniciBilgileri_ileIlgili_endPointe_requestAtarak_tokenUret__AND__jsonFiledakiVeriyi_putRequestYap()
    {
        //GET REQUEST TO SEE DETAILS OF BOOKING WITH ID:2
        given().
            spec(requestSpecification01).
        get("/booking/2").
                prettyPrint();



      //locate request body details from file
      File authorizationCredentials = new File("src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/Herokuapp_Testing/d_putRequests/request01/userCredentials.json");

      //Do Post request AND get authentication authenticationToken
      Response response =
              given().
                      contentType(ContentType.JSON).
                      spec(requestSpecification01).
                      basePath("auth").
                      body(authorizationCredentials).
                      accept(ContentType.JSON)
              .when()
                    .post();

    response.prettyPrint();

    String authenticationToken =response.then().extract().body().path("token");


    //GET JSON DATA FROM FILE
    File jsonFile = new File("src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/Herokuapp_Testing/d_putRequests/request01/booking.json");

    Response response2 =
        given().
            spec(requestSpecification01).//https://restful-booker.herokuapp.com/
            basePath("booking/").        //https://restful-booker.herokuapp.com/booking/
            pathParam("id","2").   //https://restful-booker.herokuapp.com/booking/{id}
            //header("Cookie", "token="+ authenticationToken).//DOGRU AUTHENTICATION
            cookie("token",authenticationToken).           //DOGRU AUTHENTICATION
            //cookie("Basic","YWRtaW46cGFzc3dvcmQxMjM=").    //YANLIS AUTHENTICATION
            body(jsonFile).
            contentType(ContentType.JSON).
        when().
            put("{id}");              //https://restful-booker.herokuapp.com/booking/2

    response2.prettyPrint();



    //TESTING KISMINI YAPMADIK
       // ASSERTION YAPMADIK
        //NORMALDE YAPILMALIDIR
        //SIZE ODEV OLSUN


    }

}

