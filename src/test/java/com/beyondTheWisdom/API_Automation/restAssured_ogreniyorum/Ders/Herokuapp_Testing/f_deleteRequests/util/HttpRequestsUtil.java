package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util;

import static io.netty.handler.codec.http.HttpHeaderValues.*;
import static io.restassured.RestAssured.given;


import groovyjarjarantlr4.v4.runtime.dfa.AcceptStateInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.AuthRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.BookingRequestPayload;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.constants.ApiEndpoints.BASE_URL;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.constants.ApiEndpoints.BOOKING_ENDPOINT;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.constants.ApiEndpoints.AUTH_ENDPOINT;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.valueOf;
import static org.apache.http.HttpHeaders.ACCEPT_ENCODING;
import static org.apache.http.HttpHeaders.CONNECTION;


public class HttpRequestsUtil
{
    //POST VE DELETE ICINDE AYNI ENDPOINT KULLANILIR
    //O YUZDEN ORTAK BIR TANE STRING VARIABLE OLUSTURALIM
    static String ENDPOINT = BASE_URL + BOOKING_ENDPOINT;

    public static Response createBooking(BookingRequestPayload bookingRequestPayload) {
         Response response=
                 given().
                         contentType(ContentType.JSON)
                        .accept(valueOf(APPLICATION_JSON))
                        .body(bookingRequestPayload)
                .when()
                    .post(ENDPOINT);

         response.prettyPrint();
         return response;
    }

    public static Response createToken(AuthRequestPayload authRequestPayload) {
        return given()
                .contentType(JSON)
                //HEADER ISIMLERI  ICIN(KEY)   2 TANE CLASS VAR --> com.google.common.net.HttpHeaders YA DA org.apache.http.HttpHeaders
                //HEADER DEGERLERI ICIN(VALUE) 1 TANE CLASS VAR --> io.netty.handler.codec.http.HttpHeaderValues
                .header(CONNECTION,KEEP_ALIVE)
                .header(ACCEPT_ENCODING,GZIP)
                .body(authRequestPayload)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT);
    }

    public static Response deleteBooking(String id, String authToken) {
        return given().cookie("token" , authToken).when().delete(ENDPOINT + id);
    }

    public static Response getBookingById(String id)
    {
        return given().accept(valueOf(APPLICATION_JSON)).when().get(ENDPOINT + id);

    }
}
