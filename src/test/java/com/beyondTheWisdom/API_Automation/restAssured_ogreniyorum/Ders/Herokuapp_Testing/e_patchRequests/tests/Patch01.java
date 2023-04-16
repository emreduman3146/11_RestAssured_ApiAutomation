package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.tests;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.objects.Objects;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.util.ReusableMethods;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.AuthRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.AuthResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingResponsePayload;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.util.HttpRequestsUtil.*;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.util.ReusableMethods.createBookingRequestPayloadWithFaker;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.objects.Objects.currentID;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.objects.Objects.faker;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.objects.Objects.token;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.util.HTTPRequestUtils.bookingPatchRequest;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.util.HTTPRequestUtils.bookingPostRequest;
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

        //API FUNCTIONAL AUTOMATION
            //STEP01   -> ILK OLARAK KENDI KAYDIMIZI OLUSTURALIM
                //STEP1.1  -> bookingRequestPayload OLUSTURALIM
                BookingRequestPayload bookingRequestPayload= ReusableMethods.createBookingRequestPayloadWithFaker();

                //STEP1.2  -> POST REQUEST ILE DATABASE'DE VERI CREATE EDELIM + DESERIALIZATION YAPALIM(Response Body ---> Java Object)
                com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.patchRequest.objects.Objects.currentID=bookingPostRequest(bookingRequestPayload).as(BookingResponsePayload.class).getBookingid();


            //STEP02   -> KENDI OLUSTURDUGUMUZ VERIYI PATCHLEYELIM
                //STEP2.1   -> ELIMIZDEKI REQUEST PAYLOAD'U GUCELLEYELIM(PATCHING YAPALIM)
                bookingRequestPayload.setFirstname(faker.name().firstName());
                bookingRequestPayload.setTotalprice(faker.number().numberBetween(100,500));

                //STEP2.1   -> HTTPS PATCH REQUEST YAPALIM
                Response response=bookingPatchRequest(bookingRequestPayload,currentID,token);


        //API FUNCTIONAL AUTOMATION TESTING
            //STEP01   -> ASSERT THE STATUS IS 200?
            assertThat(response.getStatusCode(), equalTo(SC_OK));

            response.prettyPrint();

    }
}
