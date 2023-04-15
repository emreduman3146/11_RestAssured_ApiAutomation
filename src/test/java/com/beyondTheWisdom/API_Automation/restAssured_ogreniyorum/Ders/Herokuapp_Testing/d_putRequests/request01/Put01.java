package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request01;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Put01 extends TestBase
{

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
                given().contentType(ContentType.JSON).
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

       // Get authentication authenticationToken
        Response response2 =
            given().
                spec(requestSpecification01).
                basePath("booking/").
                pathParam("id","2").
                header("Cookie", "token="+ authenticationToken).
                body(jsonFile).
                contentType(ContentType.JSON).
            when().
                put("{id}");

        response2.prettyPrint();

    }

}

