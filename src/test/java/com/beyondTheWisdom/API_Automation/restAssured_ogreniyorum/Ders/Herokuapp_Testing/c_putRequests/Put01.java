package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_putRequests;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.AuthResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.Booking;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.Bookingdates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.UserCredentials;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.*;

public class Put01 extends TestBase {

    @Test
    public void updateBooking()
    {
        //GET REQUEST TO SEE BOOKING WITH ID:1
        given().
            spec(requestSpecification01).
        get("/booking/1").
                prettyPrint();

        //GET JSON DATA FROM FILE
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfDate.format(new Date());

        Faker faker = new Faker();

      //  return Booking.builder()
      //          .firstname(faker.name().firstName())
      //          .lastname(faker.name().lastName())
      //          .totalprice(faker.number().numberBetween(100, 500))
      //          .depositpaid(true)
      //          .bookingdates(
      //                  Bookingdates.builder().checkin(currentDate).checkout(currentDate).build())
      //          .additionalneeds("None")
      //          .build();
        //DEFINE AUTHENTICATION CREDENTIALS
        String username = "admin";
        String password = "password123";

        UserCredentials userCredentials =
                UserCredentials.builder().username(username).password(password).build();

        // Get authentication token
        Response response =
            given().
                spec(requestSpecification01).
                //body().
                accept("application/json")
            .when()
                .post("/auth");

        response.prettyPrint();

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

