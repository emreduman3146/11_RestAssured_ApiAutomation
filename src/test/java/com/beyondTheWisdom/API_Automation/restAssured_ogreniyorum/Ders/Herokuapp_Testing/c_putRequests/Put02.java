package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_putRequests;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.UserCredentials;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Put02 extends TestBase {

    @Test
    public void updateBooking()
    {
        //GET REQUEST TO SEE BOOKING WITH ID:1
        given().
            spec(requestSpecification01).
        get("/booking/2").
                prettyPrint();


        String requestBody="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        // Get authentication token
        Response response =
                given().contentType(ContentType.JSON).
                        spec(requestSpecification01).
                        body(requestBody).
                        accept("application/json")
                .when()
                        .post("/auth");

        response.prettyPrint();

        String token=response.then().extract().body().path("token");

        //GET JSON DATA FROM FILE
        File jsonFile = new File("src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/Herokuapp_Testing/c_putRequests/booking.json");

        String jsonRequestBody="{\n" +
                "\"firstname\": \"Sallya\",\n" +
                "\"lastname\": \"Brown\",\n" +
                "\"totalprice\": 383,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2018-11-30\",\n" +
                "\"checkout\": \"2019-09-11\"\n" +
                "},\n" +
                "\"additionalneeds\":\"Wifi\"\n" +
                "}";


        // Get authentication token
        Response response2 =
            given().
                spec(requestSpecification01).
                header("Cookie", "token="+token).
                body(jsonRequestBody).
                contentType(ContentType.JSON).
                accept("application/json")
            .when()
                .put("/booking/2");

        response2.prettyPrint();

        //String token = response.as(AuthResponsePayload.class).getToken();
        //System.out.println(token);

    //       response.then()
    //           .statusCode(HttpStatus.SC_OK)
    //           .extract()
    //           .response().
    //               path("token").
    //               toString();
    //
    //   System.out.println(token);
    }

}

