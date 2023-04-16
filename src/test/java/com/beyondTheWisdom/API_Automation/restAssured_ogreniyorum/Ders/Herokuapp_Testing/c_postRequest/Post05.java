package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Post05 extends TestBase
{
    //SERIALIZATION + DESERIALIZATION KONUSU GORMUSTUK.(POST04'te)
    @Test
    public void javaFakerLibrary_ve_pojoClassLombokBuilderIleOlusturulmasi_postBookingRequestyapma__AND__GSONLibraryile_serialization_ve_Restassured_ile_deserialization_yapma()
    {
        Post04 post04=new Post04();

        //AUTOMATION STEPLERI
            //STEP01
            BookingRequestPayload bookingRequestPayload=post04.createBookingRequestPayloadWithJavaFaker();

            //STEP02-SERIALIZATION
            //Gson objesi ile yapacagiz.
            GsonBuilder gsonBuilder=new GsonBuilder();
            gsonBuilder.registerTypeAdapter(BookingResponsePayload.class, new BookingResponsePayload.Deserializer());//java konusu inner class
            Gson gson=gsonBuilder.create();


            //SERIALIZATION
            String jsonRequestPayload=gson.toJson(bookingRequestPayload);


            //STEP3->HTTP POST REQUEST OLUSTURALIM.
            Response response=given().
                    contentType(ContentType.JSON).
                    spec(requestSpecification01).//https://restful-booker.herokuapp.com/
                    body(jsonRequestPayload).
                    when().
                    post("/booking");

            response.prettyPrint();


            //STEP4
            //DESERIALIZATION
            //JSON FORMATINDAKI DATAYI---> JAVA POJO CLASS OBJECT FORMATINA DONUSTURMEK ISTIYORUZ
            BookingResponsePayload bookingResponsePayload=response.getBody().as(BookingResponsePayload.class);
             System.out.println(bookingResponsePayload);


             BookingResponsePayload bookingResponsePayload1=gson.fromJson(response.getBody().asString(),BookingResponsePayload.class);
            System.out.println(bookingResponsePayload1);

        //TESTING STEPLERI
            //STEP1
            SoftAssert softAssert=new SoftAssert();
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getFirstname(),bookingRequestPayload.getFirstname());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getLastname(),bookingRequestPayload.getLastname());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getTotalprice(),bookingRequestPayload.getTotalprice());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getDepositpaid(),bookingRequestPayload.getDepositpaid());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getBookingdates().getCheckin(),bookingRequestPayload.getBookingdates().getCheckin());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getBookingdates().getCheckout(),bookingRequestPayload.getBookingdates().getCheckout());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getAdditionalneeds(),bookingRequestPayload.getAdditionalneeds());//--->TRUE/PASS

            softAssert.assertAll();

            assertThat(bookingResponsePayload.getBookingRequestPayload().equals(bookingRequestPayload), is(true));
            assertThat(bookingResponsePayload.getBookingRequestPayload(),equalTo(bookingRequestPayload));





    }
}
