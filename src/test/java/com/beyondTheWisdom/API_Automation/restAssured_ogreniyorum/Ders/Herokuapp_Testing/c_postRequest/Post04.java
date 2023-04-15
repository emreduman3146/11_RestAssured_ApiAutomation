package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Post04 extends TestBase
{
    @Test
    public void javaFakerLibrary_ve_pojoClassLombokBuilder_ile_javaObjectOlusturalim__AND__postBookingRequestWithJsonData___JACKSON_LIBRARY_ILE_SERIALIZATION_VE_DESERIALIZATION_YAPALIM()
    {
        // Create a booking request payload using the Faker library
        BookingRequestPayload requestPayload = createBookingRequestPayloadWithFaker();

        //SERIALIZATION(Mulakatta sorulur)
        // Serialize the BookingRequestPayload to JSON using Jackson library
        //ELDEKI HERHANGI BIR DATAYI ISTENEN FORMATA DONUSTURMEK,
        //MESELA, SU AN, JSON DATA ILE CALISMAK ISTIYORUZ VE ELIMIZDEKI requestPayload(Java Objesi)'u istenilen json format'ina donusturmek, SERIALIZATION YAPMAKTIR
        String jsonRequestBody = null;
        try {
            jsonRequestBody = new ObjectMapper().writeValueAsString(requestPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize the request payload to JSON", e);
        }

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


        //DESERIALIZATION(Mulakatta sorulur)
        // Deserialize the response body to BookingResponsePayload class using Jackson library
        //UZERINDE CALISILIYOR OLMAKTA OLAN DATA FORMATININ, BASKA BIR DATA FOMRATINA DONUSTURULMESINE DENIR.
        //MESELA, REQUEST YA DA RESPONSE BODYLERDE JSON TIPINDE DATA ILE CALISIYORUZ VE BU DATAYI BookingResponsePayload(JAVA OBJESINE) DONUSTURUYORUZ
        BookingResponsePayload responsePayload = null;
        try {
            responsePayload = new ObjectMapper().readValue(response.asString(), BookingResponsePayload.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize the response body to BookingResponsePayload", e);
        }

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
