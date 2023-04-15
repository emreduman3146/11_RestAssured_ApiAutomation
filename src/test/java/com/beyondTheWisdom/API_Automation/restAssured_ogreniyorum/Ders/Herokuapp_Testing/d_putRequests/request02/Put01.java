package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.*;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.PropertiesReadWrite.getProperty;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.PropertiesReadWrite.setProperty;
import static io.restassured.RestAssured.*;

public class Put01 extends TestBase
{

    @Test
    public void Fully_Automated_Put_Request_Scenario() throws IOException {

        //POST REQUEST
            //Generate fake Java Object via BookingRequestPayload POJO Class
            BookingRequestPayload bookingPostRequestPayload = createBookingRequestPayloadWithFaker();
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),"booking",null,null,"Content-Type:application/json", bookingPostRequestPayload);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            Response postRequest_response= performRequest_doBasicAssertion_returnResponseDetails(requestSpecification01,"post",Response.class);
            //Store the TOKEN in configuration.properties file so that it can be used/reached easily at next steps
            setProperty("bookingid",postRequest_response.getBody().as(BookingResponsePayload.class).getBookingid());
            //Print in Console
            postRequest_response.prettyPrint();



        //GET REQUEST
            //Create spec with params to perform a http request
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),"booking/"+getProperty("bookingid"),null,null,null,null);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            ResponseBodyData getRequest_responseBodyData= performRequest_doBasicAssertion_returnResponseDetails(requestSpecification01,"get",ResponseBodyData.class);
            //Print in Console
            System.out.println(getRequest_responseBodyData.asPrettyString());




        //POST REQUEST TO GENERATE TOKEN
            //Generate a JavaObject via Lombok with existent data/credentials
            AuthRequestPayload authRequestPayload = AuthRequestPayload.builder().username(getProperty("username")).password(getProperty("password")).build();
            //Create spec with params to perform a http request
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),"auth",null,null,"Accept:application/json,Content-Type:application/json",authRequestPayload);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            ResponseBody authResponseBody = performRequest_doBasicAssertion_returnResponseDetails(requestSpecification01,"post",ResponseBody.class);
            //Deserialization(json --> javaObject)
            AuthResponsePayload authResponsePayload = authResponseBody.as(AuthResponsePayload.class);
            //Store the TOKEN in configuration.properties file so that it can be used/reached easily at next steps
            setProperty("token",authResponsePayload.getToken());
            //Print in Console
            authResponseBody.prettyPrint();



        //PUT REQUEST TO UPDATE THE DATABASE
            //Generate fake Java Object via BookingRequestPayload POJO Class
            BookingRequestPayload bookingPutRequestPayload = createBookingRequestPayloadWithFaker();
            //Create spec with params to perform a http request
            requestSpecification01= createRequestSpecs(getProperty("baseURI"),"booking/"+getProperty("bookingid"),null,null,"Cookie:token="+ getProperty("token")+",Content-Type:application/json", bookingPutRequestPayload);
            //Perform the httpRequest + doAssertion + returnTheWantedResponseDetails
            ResponseBody putRequest_ResponseBody = performRequest_doBasicAssertion_returnResponseDetails(requestSpecification01,"put", ResponseBody.class);
            //Deserialization(json --> javaObject)
            BookingRequestPayload bookingRequestPayload=putRequest_ResponseBody.as(BookingRequestPayload.class);
            //Print in Console
            putRequest_ResponseBody.prettyPrint();


        // Verify the bookingRequestPayload with bookingPutRequestPayload
            SoftAssert softAssertions = new SoftAssert();
            softAssertions.assertEquals(bookingRequestPayload.getFirstname(), bookingPutRequestPayload.getFirstname());
            softAssertions.assertEquals(bookingRequestPayload.getLastname(), bookingPutRequestPayload.getLastname());
            softAssertions.assertEquals(bookingRequestPayload.getTotalprice(), bookingPutRequestPayload.getTotalprice());
            softAssertions.assertEquals(bookingRequestPayload.getDepositpaid(), bookingPutRequestPayload.getDepositpaid());
            softAssertions.assertEquals(bookingRequestPayload.getBookingdates().getCheckin(), bookingPutRequestPayload.getBookingdates().getCheckin());
            softAssertions.assertEquals(bookingRequestPayload.getBookingdates().getCheckout(), bookingPutRequestPayload.getBookingdates().getCheckout());
            softAssertions.assertEquals(bookingRequestPayload.getAdditionalneeds(), bookingPutRequestPayload.getAdditionalneeds());
            softAssertions.assertAll();


    }


    //3 UTILITIES METHODS
    public BookingRequestPayload createBookingRequestPayloadWithFaker() {

        Faker faker = new Faker();
        BookingDates bookingDates = BookingDates.builder()
                .checkin(faker.date().future(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString())
                .checkout(faker.date().future(20, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString())
                .build();

        return BookingRequestPayload.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(500, 2000))
                .depositpaid(faker.bool().bool())
                .bookingdates(bookingDates)
                .additionalneeds(faker.lorem().sentence())
                .build();
    }

    public static RequestSpecification createRequestSpecs(String baseURI, String basePath, String queryParam, String pathParam, String headers, Object requestBody) {


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        if (baseURI != null && !baseURI.isEmpty()) {
            requestSpecBuilder.setBaseUri(baseURI);
        }

        if (basePath != null ) {
            requestSpecBuilder.setBasePath(basePath);
        }
        if (requestBody != null) {
            requestSpecBuilder.setBody(requestBody);
        }

        if (queryParam != null && !queryParam.isEmpty()) {
            String[] queryParams = queryParam.split(",");
            for (String query : queryParams) {
                String[] keyValue = query.split(":");
                requestSpecBuilder.addQueryParam(keyValue[0], keyValue[1]);
            }
        }

        if (pathParam != null && !pathParam.isEmpty()) {
            String[] pathParams = pathParam.split(",");
            for (String path : pathParams) {
                String[] keyValue = path.split(":");
                requestSpecBuilder.addPathParam(keyValue[0], keyValue[1]);
            }
        }

        if (headers != null && !headers.isEmpty()) {
            String[] headerArray = headers.split(",");
            for (String header : headerArray) {
                String[] keyValue = header.split(":");
                requestSpecBuilder.addHeader(keyValue[0], keyValue[1]);
            }
        }

        return requestSpecBuilder.build();
    }

    public static <T> T performRequest_doBasicAssertion_returnResponseDetails(RequestSpecification requestSpecification, String requestType, Class<T> clazz)
    {
        //PERFROM REQUEST
        Response response;
        switch (requestType.toLowerCase())
        {
            case "get":response=given().spec(requestSpecification).when().get();break;
            case "post":response=given().spec(requestSpecification).when().post();break;
            case "put":response=given().spec(requestSpecification).when().put();break;
            case "patch":response=given().spec(requestSpecification).when().patch();break;
            case "delete":response=given().spec(requestSpecification).when().delete();break;
            case "options":response=given().spec(requestSpecification).when().options();break;
            case "head":response=given().spec(requestSpecification).when().head();break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType.toLowerCase());
        }

        //DO BASIC ASSERTION
        response.then().assertThat().statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON);


        //RETURN RESPONSE DETAILS
        if (clazz == Response.class) {
            return clazz.cast(response);
        } else if (clazz == ResponseBodyData.class) {
            return clazz.cast(response.getBody());
        }else if (clazz == ResponseBody.class) {
            return clazz.cast(response.getBody());
        }else if (clazz == Headers.class) {
            return clazz.cast(response.getHeaders());
        } else if (clazz == Cookies.class) {
            return clazz.cast(response.getCookies());
        } else {
            throw new IllegalArgumentException("Invalid class: " + clazz.getSimpleName());
        }
    }


}

