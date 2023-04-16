package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.tests;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.objects.Objects;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.AuthRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.AuthResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingResponsePayload;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.util.HttpRequestsUtil.*;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.util.ReusableMethods.createBookingRequestPayloadWithFaker;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.constants.Constants.*;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.objects.Objects.*;

public class Patch01
{

    @BeforeMethod
    void createTokenForAuthentication()
    {
        //CREATE A REQUEST BODY BY POJO CLASS
        AuthRequestPayload authRequestPayload = AuthRequestPayload.builder().username(username).password(password).build();

        //POST REQUEST TO GENERATE TOKEN
        Response response = generateToken(authRequestPayload);

        //DESERIALIZATION(Response Body --->>> Pojo Object)
        token = response.as(AuthResponsePayload.class).getToken();

        //ASSERT THE STATUS CODE
        assertThat(response.statusCode(), equalTo(SC_OK));

        response.prettyPrint();
    }

    @Test
    void patchBookingRequest()
    {
        //CREATE A REQUEST BODY BY POJO CLASS
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayloadWithFaker();

        //POST REQUEST TO CREATE A BOOKING + DESERIALIZATION(JSON-->JavaObject)
        int id = bookingPostRequest(bookingRequestPayload)
                .as(BookingResponsePayload.class)
                .getBookingid();

        //PATCHING(PARTIALLY UPDATING) THE JAVA OBJECT
        bookingRequestPayload.setTotalprice(Objects.faker.number().numberBetween(100, 500));

        //POST THE PATCHED POJO OBJECT VIA TOKEN
        Response response = bookingPatchRequest(bookingRequestPayload, id, Objects.token);

        //ASSERT THE STATUS CODE
        assertThat(response.statusCode(), equalTo(SC_OK));

        response.prettyPrint();

    }
}
