package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.tests;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.*;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.util.PropertiesUtil.*;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.util.RequestTypes.*;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.util.ReusableMethods.*;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Put01 extends TestBase
{

    @Test
    public void Fully_Automated_Put_Request_Scenario() throws IOException {

        //POST REQUEST
            //Generate fake Java Object via BookingRequestPayload POJO Class
            BookingRequestPayload bookingPostRequestPayload = createBookingRequestPayloadWithFaker();
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),getProperty("basePath_booking"),null,null,"Content-Type:application/json", bookingPostRequestPayload);

            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            Response postRequest_response=doBasicAssertion(performRequest(requestSpecification01,POST),SC_OK,JSON,Response.class);

            //Store the TOKEN in config.properties file so that it can be used/reached easily at next steps
            setProperty("bookingid",postRequest_response.getBody().as(BookingResponsePayload.class).getBookingid());
            //Print in Console
            postRequest_response.prettyPrint();



        //GET REQUEST
            //Create spec with params to perform ReusableMethods http request
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),getProperty("basePath_booking")+"/"+getProperty("bookingid"),null,null,null,null);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            ResponseBodyData getRequest_responseBodyData= doBasicAssertion(performRequest(requestSpecification01,GET),SC_OK,JSON,ResponseBodyData.class);
            //Print in Console
            System.out.println(getRequest_responseBodyData.asPrettyString());




        //POST REQUEST TO GENERATE TOKEN
            //Generate ReusableMethods JavaObject via Lombok with existent data/credentials
            AuthRequestPayload authRequestPayload = AuthRequestPayload.builder().username(getProperty("username")).password(getProperty("password")).build();
            //Create spec with params to perform ReusableMethods http request
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),getProperty("basePath_Auth"),null,null,"Accept:application/json,Content-Type:application/json",authRequestPayload);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            ResponseBody authResponseBody = doBasicAssertion(performRequest(requestSpecification01,POST),SC_OK,JSON,ResponseBody.class);
            //Deserialization(json --> javaObject)
            AuthResponsePayload authResponsePayload = authResponseBody.as(AuthResponsePayload.class);
            //Store the TOKEN in config.properties file so that it can be used/reached easily at next steps
            setProperty("token",authResponsePayload.getToken());
            //Print in Console
            authResponseBody.prettyPrint();



        //PUT REQUEST TO UPDATE THE DATABASE
            //Generate fake Java Object via BookingRequestPayload POJO Class
            BookingRequestPayload bookingPutRequestPayload = createBookingRequestPayloadWithFaker();
            //Create spec with params to perform ReusableMethods http request
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),getProperty("basePath_booking")+"/"+getProperty("bookingid"),null,null,"Cookie:token="+ getProperty("token")+",Content-Type:application/json", bookingPutRequestPayload);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            ResponseBody putRequest_ResponseBody = doBasicAssertion(performRequest(requestSpecification01, PUT),SC_OK,JSON, ResponseBody.class);
            //Deserialization(json --> javaObject)
            BookingRequestPayload bookingRequestPayload=putRequest_ResponseBody.as(BookingRequestPayload.class);
            //Print in Console
            putRequest_ResponseBody.prettyPrint();


        // Verify the bookingRequestPayload with bookingPutRequestPayload
          //SoftAssert softAssertions = new SoftAssert();
          //softAssertions.assertEquals(bookingRequestPayload.getFirstname(), bookingPutRequestPayload.getFirstname());
          //softAssertions.assertEquals(bookingRequestPayload.getLastname(), bookingPutRequestPayload.getLastname());
          //softAssertions.assertEquals(bookingRequestPayload.getTotalprice(), bookingPutRequestPayload.getTotalprice());
          //softAssertions.assertEquals(bookingRequestPayload.getDepositpaid(), bookingPutRequestPayload.getDepositpaid());
          //softAssertions.assertEquals(bookingRequestPayload.getBookingdates().getCheckin(), bookingPutRequestPayload.getBookingdates().getCheckin());
          //softAssertions.assertEquals(bookingRequestPayload.getBookingdates().getCheckout(), bookingPutRequestPayload.getBookingdates().getCheckout());
          //softAssertions.assertEquals(bookingRequestPayload.getAdditionalneeds(), bookingPutRequestPayload.getAdditionalneeds());
          //softAssertions.assertAll();
          //
            assertThat(bookingRequestPayload.equals(bookingPutRequestPayload), is(true));
            assertThat(bookingRequestPayload,equalTo(bookingPutRequestPayload));



    }




}

