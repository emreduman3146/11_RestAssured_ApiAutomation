package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.testbase;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.objects.Objects;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.HttpRequestsUtil;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.AuthRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.AuthResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.BookingResponsePayload;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.IOException;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.objects.Objects.pathParam;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.objects.Objects.token;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.constants.ResponsePayloadMessages.NOT_FOUND;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.HttpRequestsUtil.createToken;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.PayloadCreationUtil.createBookingRequestPayload;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.PropertiesReadWrite.getProperty;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.PropertiesReadWrite.setProperty;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase
{
    @BeforeSuite(groups = {"ByID"})
    public static void test_configurations()
    {
        pathParam="bookingID_pathParam";
        System.out.println(pathParam);
    }


    @BeforeTest(groups = {"ByID"})
    public void postRequest_createBooking_returns200_OK() throws IOException
    {
        //POST HTTPS REQUEST TO CREATE A BOOKING RECORD IN SQL TABLE
        //DESERIALIZATION THE RESPONSE BODY(JSON) --> JavaObject
        //STORING A SPECIFIC VALUE FROM
        int bookingID = HttpRequestsUtil.createBooking(createBookingRequestPayload()).
                getBody().
                as(BookingResponsePayload.class).
                getBookingid();

        //STORE THE TEMPORARY VALUE IN CONFIGURATION.PROPERTIES FILE
        setProperty(pathParam,bookingID);

    }

    @BeforeGroups("ByID")
    public void setupGroup1() {
        System.out.println("BeforeGroups->ByID");
    }

    @BeforeClass(groups = {"ByID"})
    public void postRequest_createToken()
    {
        //POJO CLASS + LOMBOK BUILDER ILE ->>> JAVA OBJECT OLUSTURULDU
        AuthRequestPayload authRequestPayload = AuthRequestPayload.builder().username(getProperty("username")).password(getProperty("password")).build();

        //POST REQUEST TO CREATE TOKEN
        //DESERIALIZATION THE RESPONSE BODY(JSON) --> JavaObject
        //STORING A SPECIFIC VALUE FROM
        Response response = createToken(authRequestPayload);
        token = response.as(AuthResponsePayload.class).getToken();

        //DO ASSERTION VIA HAMCREST(Matchers Class)
        assertThat(response.statusCode(), equalTo(SC_OK));

        response.prettyPrint();

    }

    @BeforeMethod
    public void before()
    {
        System.out.println("beforeMethod");
    }


    @AfterMethod(groups = {"ByID"})
    public void getRequest_theDeletedBooking_ByID_returns404_NotFound()
    {
        //GET HTTPS REQUEST BY ID
        //STORE THE RESPONSE DETAILS
        Response response = HttpRequestsUtil.getBookingById(getProperty(Objects.pathParam));

        //DO ASSERTION VIA HAMCREST(Matchers Class)
        //STATUS CODE + RESPONSE BODY
        assertThat(response.statusCode(), equalTo(SC_NOT_FOUND));
        assertThat(response.getBody().asPrettyString(), equalTo(NOT_FOUND));

        response.prettyPrint();
    }

}
