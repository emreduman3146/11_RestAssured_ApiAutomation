package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02.util;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;

import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class ReusableMethods
{
    //3 UTILITIES METHODS
    public static BookingRequestPayload createBookingRequestPayloadWithFaker() {

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


    public static <T> T doBasicAssertion(Response response, int scOk, ContentType json, Class<T> clazz)
    {
        response=response.then().assertThat().statusCode(SC_OK).contentType(JSON).extract().response();


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


    public static Response performRequest(RequestSpecification requestSpecification, RequestTypes requestType)
    {
        //PERFROM REQUEST
        Response response;
        RequestSpecification requestSpec=given().spec(requestSpecification).when();
        switch (requestType)
        {
            case GET:response=requestSpec.get();break;
            case POST:response=requestSpec.post();break;
            case PUT:response=requestSpec.put();break;
            case PATCH:response=requestSpec.patch();break;
            case DELETE:response=requestSpec.delete();break;
            case OPTIONS:response=requestSpec.options();break;
            case HEAD:response=requestSpec.head();break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType.name());
        }
        return response;

    }


    public static void assertHeaders(Response response, Map<String, Object> expectedHeaders)
    {
        for (Map.Entry<String, Object> entry : expectedHeaders.entrySet()) {
            String headerName = entry.getKey();
            Object expectedValue = entry.getValue();
            Object actualValue = response.getHeader(headerName);

            if (actualValue == null) {
                throw new AssertionError("Response header not found: " + headerName);
            }

            if (!expectedValue.equals(actualValue)) {
                throw new AssertionError("Response header '" + headerName + "' mismatch: expected "
                        + expectedValue + ", but found " + actualValue);
            }
        }
    }


}
