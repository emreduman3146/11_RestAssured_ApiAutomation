package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.getRequest;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class Get01 extends TestBase
{
    @Test
    public void requestOlustururken_baseUri_basePath_header_kullanimi()
    {
        //zincirleme
        Response response=
        given().
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
        when().
            get();

        response.prettyPrint();

        response.
        then().
            assertThat().
            statusCode(200);

        //bir programlamaci olarak method zincirleme mantigini siz de yapabilirsiniz?
    }

    @Test
    public void queryParam_kullanimi()
    {
        Response response=
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("/workspaces").
                        queryParam("type","team").
                        header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                when().
                        get();

        response.prettyPrint();

    }


    @Test
    public void multiple_queryParam_kullanimi()
    {
        Response response=
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("/workspaces").
                        queryParam("type","team").
                        queryParam("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                        //header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                when().
                        get();

        response.prettyPrint();

    }

    @Test
    public void hashMap_ile_queryParam_kullanimi()
    {
        Map queryParams = new HashMap();
        queryParams.put("type","team");
        queryParams.put("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c");

        Response response=
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("/workspaces").
                        queryParams(queryParams).
                when().
                        get();

        response.prettyPrint();

    }


    @Test
    public void pathParamin_basePath_veya_get_icinde_kullanimi()
    {

        //https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac
        //https://api.getpostman.com/workspaces/{{workspaceid}}

        Response response=
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("/workspaces").
                        pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                        header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                when().
                        get("/{workspaceid}");

        response.prettyPrint();

    }


    @Test
    public void requestSpecification_objesi_olusturup_icerisine_tum_gerekli_request_elemanlarini_depolayalim() {

        //https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac
        //https://api.getpostman.com/workspaces/{{workspaceid}}

        //bu request torbasina bilgileri koyarak dolduralim.
        requestSpecification02.
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                queryParam("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("Accept-Encoding","gzip, deflate, br" ).
                accept(JSON);

        Response response =
                given().
                        spec(requestSpecification02).
                        when().
                        get("/{workspaceid}");

        response.prettyPrint();

    }


    @Test
    public void hashMap_ile_queryParam_pathParam_Header_kullanimi() {


        Map headers = new HashMap();
        headers.put("Accept-Encoding","gzip, deflate, br" );
        headers.put("Accept","*/*" );

        Map pathParams = new HashMap();
        pathParams.put("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac");


        Map queryParams = new HashMap();
        queryParams.put("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c");



        //bu request torbasina bilgileri koyarak dolduralim.
        requestSpecification02.
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                pathParams(pathParams).
                queryParams(queryParams).
                headers(headers);


        //AUTOMATION SCRIPTLARIMIZ
        Response response =
                given().
                        spec(requestSpecification02).
                when().
                        get("/{workspaceid}");

        response.prettyPrint();

    }



    @Test
    public void given_when_then_basamaklarini_detaylica_isleyelim__AND__extract_methodu_kullanimini_ogrenelim()
    {
        //BASE URI VE BASE PATH TANIMLAMANIN 3. FARKLI YOLU
        RestAssured.baseURI="https://api.getpostman.com";
        RestAssured.basePath="/workspaces";

        RestAssured.requestSpecification=
        given().accept(JSON);
        requestSpecification.pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac");
        requestSpecification.queryParam("apikey","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c");
        requestSpecification.header("Accept-Encoding","gzip, deflate, br" );

        Response response=
                requestSpecification.when().
                        get("/{workspaceid}");

        //VALIDATION
        ValidatableResponse validatableResponse=response.then().assertThat().statusCode(200);
        validatableResponse.and().contentType(JSON);
        validatableResponse.and().header("Server","nginx");

        //given-when-then asamalri bitti ama ben ilgili response almak kaydetmek istiyorum ki ilerde satir satir verification ypabileyim.
        ExtractableResponse extractableResponse=validatableResponse.extract();
        String prettyBody=extractableResponse.asPrettyString();

        System.out.println(prettyBody);


    }


    @Test
    public void given_when_then_assertThat_compulsory_degildir__BUT__assertion_yapiyorsak_then_kullanilir()
    {
        //https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac
        //https://api.getpostman.com/workspaces/{{workspaceid}}


        //given().//pre-condition, bana ne vermek istiyorsun, testine baslamadan ne ayar yapmak istiyorsun
        //when(). //action almak istedigimiz zaman
                get("https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac?apikey=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
        then().
            //assertThat(). // code readibility
                statusCode(SC_OK).
                contentType(JSON);

    }

}
