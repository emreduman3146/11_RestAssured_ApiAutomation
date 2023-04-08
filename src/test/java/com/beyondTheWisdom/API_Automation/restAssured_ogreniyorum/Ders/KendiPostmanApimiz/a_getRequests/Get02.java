package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.a_getRequests;

import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class Get02
{

    @Test
    public void logging_for_request_and_response()
    {
        given().
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces").
                //pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                accept(ContentType.JSON).//accept("application/json").
                header("Accept-Encoding", "gzip, deflate, br").
                log().parameters().
                log().method().        // Request method:	GET
                log().uri().
                log().params().
                log().headers().
                when().
                get().
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
                log().status().                                             //HTTP/1.1 200 OK
                log().ifStatusCodeIsEqualTo(HttpStatus.SC_ACCEPTED).
                log().body().                                               //Sadece response body
                log().headers().                                            //sadece response header
                log().everything().                                         //status - body - header
                log().all();                                                //status - body - header

    }

    @Test
    public void log_iferror()
    {
        given().
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces").
                //pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                accept(ContentType.JSON).//accept("application/json").
                header("Accept-Encoding", "gzip, deflate, br").

        when().
                get().
        then().
            assertThat().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
            log().ifValidationFails().
            log().ifError();                                                //status - body - header

    }


    @Test
    public void log_config()
    {
        given().
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces").
                //pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                accept(ContentType.JSON).//accept("application/json").
                header("Accept-Encoding", "gzip, deflate, br").
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
        when().
                get().
        then().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                contentType(ContentType.JSON);

    }


    @Test
    public void log_blacklist()
    {
        given().
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces").
                //pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                //queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("X-API-Key", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-API-Key"))).
                log().all().
        when().
                get().
        then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON);

    }


}
