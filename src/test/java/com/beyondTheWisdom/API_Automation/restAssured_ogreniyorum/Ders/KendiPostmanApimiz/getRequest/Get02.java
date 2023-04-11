package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.getRequest;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;

public class Get02
{
    @Test
    public void logging_for_request_and_response()
    {
        RestAssured.
            given().
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParam("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("Accept-Encoding","gzip, deflate, br" ).
                accept(JSON).
                //log().params().
                //log().uri().
                //log().method().
                //log().headers().
            when().
                get("/{workspaceid}").
            then().
                assertThat().
                    log().ifValidationFails().
                    //log().ifError().
                    contentType(JSON).
                    statusCode(SC_OK);
                    //log().status().
                    //log().headers().
                    ////log().everything().
                    //log().all().
                    //log().ifStatusCodeIsEqualTo(SC_OK).
                    ;

    }


    @Test
    public void log_config()
    {
        RestAssured.
                given().
                    baseUri("https://api.getpostman.com").
                    basePath("/workspaces").
                    pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                    queryParam("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                    header("Accept-Encoding","gzip, deflate, br" ).
                    accept(JSON).
                    config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                when().
                    get("/{workspaceid}").
                then().
                    assertThat().
                    contentType(JSON).
                    statusCode(SC_OK);
    }

    //GITHUB BUSINESS ANALYST-PROJECT MANAGER
    //JENKINS TOOL->  TEST01 RUN (BUTONA TIKLAR)


    @Test
    public void log_BLACKLIST()
    {
        RestAssured.
                given().
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("Accept-Encoding","gzip, deflate, br" ).
                accept(JSON).
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-API-Key"))).
                log().all().
                when().
                get("/{workspaceid}").
                then().
                assertThat().
                contentType(JSON).
                statusCode(SC_OK);
    }

}
