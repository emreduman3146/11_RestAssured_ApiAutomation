package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Post05 extends TestBase
{
    @Test
    public void javaFakerLibrary_ve_pojoClassLombokBuilder_ile_javaObjectOlusturalim__AND__postBookingRequestWithJsonData___GSON_library_ile_SERIALIZATION_ve_restAssured_ile_DESERIALIZATION_yapalim()
    {
        // Create a booking request payload using the Faker library
        BookingRequestPayload requestPayload = createBookingRequestPayloadWithFaker();

        //SERIALIZATION
        Gson gson = new GsonBuilder().create();
        String jsonRequestBody = gson.toJson(requestPayload);

        // Send the POST request to create the booking
        Response response = given().
                contentType(ContentType.JSON).
                spec(requestSpecification01).
                body(jsonRequestBody).
                post("/booking");

        // Log the response body
        response.prettyPrint();

        // Verify the response status code is 200 OK
        response.then().assertThat().statusCode(200);


        //DESERIALIZATION
        //BookingResponsePayload responsePayload = gson.fromJson(response.asString(), BookingResponsePayload.class);
        BookingResponsePayload responsePayload = response.getBody().as(BookingResponsePayload.class);
        System.out.println(responsePayload);


        // Verify the response body has the expected data
        SoftAssert softAssertions = new SoftAssert();
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getFirstname(),requestPayload.getFirstname());
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getLastname(),requestPayload.getLastname());
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getTotalprice(),requestPayload.getTotalprice());
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getDepositpaid(),requestPayload.getDepositpaid());
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getBookingdates().getCheckin(),requestPayload.getBookingdates().getCheckin());
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getBookingdates().getCheckout(),requestPayload.getBookingdates().getCheckout());
        softAssertions.assertEquals(responsePayload.getBookingRequestPayload().getAdditionalneeds(),requestPayload.getAdditionalneeds());
        softAssertions.assertAll();
    }

    private BookingRequestPayload createBookingRequestPayloadWithFaker() {
        Faker faker = new Faker();
        String checkin = faker.date().future(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        String checkout = faker.date().future(20, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();

        BookingRequestPayload bookingRequestPayload=
                BookingRequestPayload.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(500, 2000))
                .depositpaid(faker.bool().bool())
                .bookingdates(BookingDates.builder().checkin(checkin).checkout(checkout).build())
                .additionalneeds(faker.lorem().sentence())
                .build();
        System.out.println(bookingRequestPayload);
        return bookingRequestPayload;
    }

}
