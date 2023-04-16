package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.util;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.AuthRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.constants.ApiEndpoints.*;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.constants.Headers.MEDIA_TYPE_JSON;
import static io.restassured.RestAssured.given;

public class HttpRequestsUtil
{
    public static Response bookingPostRequest(BookingRequestPayload bookingRequestPayload) {
        return given().contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .body(bookingRequestPayload)
                .when()
                .post(BASE_URL + BOOKING_ENDPOINT);
    }

    public static Response bookingPatchRequest(BookingRequestPayload bookingRequestPayload, int id, String authToken) {
        return given().contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .cookie("token",authToken)
                .body(bookingRequestPayload)
                .when()
                .patch(BASE_URL + BOOKING_ENDPOINT + id);
    }


    public static Response generateToken(AuthRequestPayload authRequestPayload) {
        return given().contentType(ContentType.JSON)
                .body(authRequestPayload)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT);
    }

}
