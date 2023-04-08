package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.a_getRequests;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Get01 extends TestBase {

    /*
    positive Scenario:
    When I send a GET REQUEST to REST API END POINT
    "https://dummy.restapiexample.com/api/v1/employees"
    and Accept-type is "applicaiton/json"
    then
    HTTP Status-Code is 200
    and Response Format is "application/json"
    and employee number is 24
    and "" is one of employee
    and 21,61,23 should be among  the employees ages

    https://dummy.restapiexample.com/ -->WEBSITE API DOCUMENTATION
     */

    @Test
    public void requestOlustururken_baseUri_basePath_header_kullanimi()
    {
        Response response= given().
                    baseUri("https://api.getpostman.com/").
                    basePath("/workspaces").
                    header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                    accept(ContentType.JSON).//accept("application/json").
                when().
                    get();

        response.prettyPrint();
    }

    @Test
    public void queryParam_kullanimi()
    {
        //--> https://api.getpostman.com/workspaces?type=personal
        Response response= given().
                    baseUri("https://api.getpostman.com/").
                    basePath("/workspaces").
                    header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                    queryParam("type","personal").
                    accept(ContentType.JSON).//accept("application/json").
                when().
                    get();

        response.prettyPrint();

    }




    @Test
    public void multiple_queryParam_kullanimi()
    {
        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response response= given().
                    baseUri("https://api.getpostman.com/").
                    basePath("/workspaces").
                    queryParam("type","personal").
                    queryParam("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                    accept(ContentType.JSON).//accept("application/json").
                when().
                    get();

        response.prettyPrint();
    }


    @Test
    public void hashMap_ile_queryParam_kullanimi()
    {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("type", "personal");
        queryParams.put("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c");


        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response response= given().
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces").
                queryParams(queryParams).
                accept(ContentType.JSON).//accept("application/json").
                when().
                get();

        response.prettyPrint();

    }


    @Test
    public void pathParamin_basePath_veya_get_icinde_kullanimi()
    {
        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response response= given().
                    baseUri("https://api.getpostman.com/").
                    basePath("/workspaces/{workspaceid}").
                    pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                    queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                    accept(ContentType.JSON).//accept("application/json").
                when().
                    get();

        response.prettyPrint();



        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c

                given().
                    baseUri("https://api.getpostman.com/").
                    basePath("/workspaces").
                    pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                    queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                    accept(ContentType.JSON).//accept("application/json").
                when().
                    get("/{workspaceid}");

        response.prettyPrint();

    }


    @Test
    public void requestSpecification_objesi_olusturup_icerisine_tum_gerekli_request_elemanlarini_depolama()
    {
        requestSpecification02.
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces/{workspaceid}").
                pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("Accept-Encoding","gzip, deflate, br").
                accept(ContentType.JSON);

        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response response= given().
                    spec(requestSpecification02).
                when().
                    get();

        response.prettyPrint();

        //requestSpecification02'i SIFIRLAYALIM
        requestSpecification02=new RequestSpecBuilder().
                setBaseUri("").//BASE URL
                build();

        requestSpecification02.
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                pathParams("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParams("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("Accept-Encoding","gzip, deflate, br").
                accept(ContentType.JSON);

        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response response2= given().
                spec(requestSpecification02).
                when().
                get("/{workspaceid}");

        response2.prettyPrint();


    }


    @Test
    public void hashMap_ile_pathParams_queryParams_headers_kullanimi()
    {

        Map<String,Object> pathParams=new HashMap();
        pathParams.put("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac");

        Map<String,Object> queryParams=new HashMap();
        queryParams.put("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c");

        Map<String,Object> headers=new HashMap();
        headers.put("Accept-Encoding","gzip, deflate, br");

        requestSpecification02.
                baseUri("https://api.getpostman.com/").
                basePath("/workspaces/{workspaceid}").
                pathParams(pathParams).
                queryParams(queryParams).
                headers(headers).
                accept(ContentType.JSON);

        //--> https://api.getpostman.com/workspaces?type=personal&X-API-Key=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response response= given().
                spec(requestSpecification02).
                get();

        response.prettyPrint();
    }


    @Test
    public void given_when_then_basamaklarini_detaylica_isleyelim__AND__extract_kullanimi_ogrenelim()
    {
        //BASEURI'yi tanimla
        RestAssured.baseURI= "https://api.getpostman.com/";
        RestAssured.basePath="workspaces/";

        //REQUEST OLUSTURUYORUZ
        RequestSpecification httpRequest = RestAssured.given().accept(ContentType.JSON);
        httpRequest=httpRequest.pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac");
        httpRequest=httpRequest.queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c");
        httpRequest=httpRequest.header("Accept-Encoding", "gzip, deflate, br");
        httpRequest=httpRequest.accept(ContentType.ANY);

        //Yukaridaki Request'in devamini getirip, queryParam ekliyoruz ve request tipimizi koyuyoruz.
        Response response = httpRequest.when().get("{workspaceid}");

        //VALIDATION YAPILIYOR
        ValidatableResponse validatableResponse=response.then().assertThat();
        validatableResponse.and().statusCode(200);
        validatableResponse.and().contentType(ContentType.JSON);
        validatableResponse.header("Server","nginx");

        //BIR RESPONSE'U VALIDATE ETTIKTEN SONRA RESPONSE'U YAZDIRMAK/STRING'E DONUSTURMEK ISTERSEK KULLANILIR
        ExtractableResponse extractableResponse=validatableResponse.extract();

        String extractedResponse= extractableResponse.asPrettyString();

        System.out.println(extractedResponse);

    }



    @Test
    public void given_when_assertThat_compulsory_degildir__BUT__assertionYapiyorsak_then_kullanilir()
    {

        //given(). // --> ZORUNLU DEGILDIR - OKUNAKLIGI ARTTIRIR - CODE MAINTAINABILITY SAGLAR
            //baseUri("https://api.getpostman.com/").
            //basePath("/workspaces").
            //pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
            //queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
            //accept(ContentType.JSON).//accept("application/json").
        //when().  // --> ZORUNLU DEGILDIR - OKUNAKLIGI ARTTIRIR - CODE MAINTAINABILITY SAGLAR
            get("https://api.getpostman.com/workspaces?apikey=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
        then().
            //assertThat(). // --> ZORUNLU DEGILDIR - OKUNAKLIGI ARTTIRIR
            statusCode(HttpStatus.SC_OK).
            contentType(ContentType.JSON);

    }

}
