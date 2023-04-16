package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request01;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Base64;

import static io.restassured.RestAssured.given;


public class Put02 extends TestBase
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


        //GET JSON DATA FROM FILE
        File jsonFile = new File("src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/Herokuapp_Testing/d_putRequests/request01/booking.json");

        //BASIC AUTHENTICATION-USERNAME/PASSWORD
        String credentials="admin:password123";
        String encodedCredentialsAsString = Base64.getEncoder().encodeToString(credentials.getBytes());


        Response response2 =
        given().
            spec(requestSpecification01).//https://restful-booker.herokuapp.com/
            basePath("booking/").        //https://restful-booker.herokuapp.com/booking/
            pathParam("id","2").   //https://restful-booker.herokuapp.com/booking/{id}
            //header("Authorization","Basic "+encodedCredentialsAsString).
            //header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=").
            auth().preemptive().basic("admin", "password123").
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

