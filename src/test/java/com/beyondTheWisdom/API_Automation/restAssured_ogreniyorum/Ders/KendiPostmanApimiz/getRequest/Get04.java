package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.getRequest;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class Get04 {

    @Test
    public void assertionYaptiktanSonra_uzerindeCalistigimizResponse_extractEtmek() {

        //https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac?apikey=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response responseBody_payLoad =
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("workspaces").
                        pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                        queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                        header("Accept-Encoding", "gzip, deflate, br").
                        accept(JSON).
                        config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                        when().
                        get("{workspaceid}").
                        then().
                        assertThat().
                        contentType(JSON).
                        statusCode(SC_OK).
                        extract().
                        response();

        responseBody_payLoad.prettyPeek();

        System.out.println(responseBody_payLoad.path("workspace.collections[0].name").toString());
    }


    @Test
    public void extractKullanimiSonrasi_JsonPathKullanalim() {

        //https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac?apikey=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        Response responseBody_payLoad =
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("workspaces").
                        pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                        queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                        header("Accept-Encoding", "gzip, deflate, br").
                        accept(JSON).
                        config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                when().
                        get("{workspaceid}").
                then().
                    assertThat().
                        contentType(JSON).
                        statusCode(SC_OK).//API-MICROSERVICE TESTING - ILK KRITER STATUS CODUN DOGRULANMASI
                    extract().
                        response();

        responseBody_payLoad.prettyPrint();

        //JSON DATA ICERSINDE HAZIR METHODLARI SAYESINDE VERILERI ALMAMIZI SAGLAR
        JsonPath jsonPath=new JsonPath(responseBody_payLoad.asString());
        String description=jsonPath.getString("workspace.description");
        System.out.println(description);

        System.out.println((String) jsonPath.get("workspace.description"));

        //jsonPath.getInt("groovyPath");
        HashMap<Object,Object> collection0=(HashMap<Object, Object>) jsonPath.getMap("workspace.collections[0]");
        System.out.println(collection0.get("id"));

        //JSON'DAKI ARRAY ESITTIR JAVADA LIST
        System.out.println(jsonPath.getList("workspace.collections").get(5));
        System.out.println((List)jsonPath.get("workspace.collections"));


    }



    @Test
    public void extractKullanimiSonrasi_JsonPath_from_methodu_kullanalim() {

        //https://api.getpostman.com/workspaces/4bed1ef1-11ef-4abe-875b-0ab979fb80ac?apikey=PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c
        String responseBody_payLoad =
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("workspaces").
                        pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                        queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                        header("Accept-Encoding", "gzip, deflate, br").
                        accept(JSON).
                        config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                        when().
                        get("{workspaceid}").
                        then().
                        assertThat().
                        contentType(JSON).
                        statusCode(SC_OK).//API-MICROSERVICE TESTING - ILK KRITER STATUS CODUN DOGRULANMASI
                        extract().
                        response().asPrettyString();


        //JSON DATA ICERSINDE HAZIR METHODLARI SAYESINDE VERILERI ALMAMIZI SAGLAR
        JsonPath jsonPath=JsonPath.from(responseBody_payLoad);

        String description=jsonPath.getString("workspace.description");
        System.out.println(description);

        System.out.println((String) jsonPath.get("workspace.description"));

        //jsonPath.getInt("groovyPath");
        HashMap<Object,Object> collection0=(HashMap<Object, Object>) jsonPath.getMap("workspace.collections[0]");
        System.out.println(collection0.get("id"));

        //JSON'DAKI ARRAY ESITTIR JAVADA LIST
        System.out.println(jsonPath.getList("workspace.collections").get(5));
        System.out.println((List)jsonPath.get("workspace.collections"));


    }



    @Test
    public void extractKullanimiSonrasi_JsonPath_from_methodu_kullanalim2() throws IOException {

        //HTTP GET REQUEST--> ASSERTION YAPMISIZ + EXTRACTION
        String responseBody_payLoad =
                given().
                        baseUri("https://api.getpostman.com").
                        basePath("workspaces").
                        pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                        queryParam("apikey", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                        header("Accept-Encoding", "gzip, deflate, br").
                        accept(JSON).
                        config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                        when().
                        get("{workspaceid}").
                        then().
                        assertThat().
                        contentType(JSON).
                        statusCode(SC_OK).//API-MICROSERVICE TESTING - ILK KRITER STATUS CODUN DOGRULANMASI
                        extract().
                        response().asPrettyString();


        //JAVADA DOSYA OKUMA YAZMANIN YUZLERCE FARKLI YOLU VAR.
        //JSON
        String jsonFilePath="src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/KendiPostmanApimiz/getRequest/workspaces.json";
        Path filePath = Paths.get(jsonFilePath);

        //ELIMIZDEKI STRING DATAYI -----> .JSON FILE'ININ ICINE YAZMAK ISTIYORUZ. || JSON FILE PATH
        Files.write(filePath, responseBody_payLoad.getBytes());
        //JAVADA STRING CLASSINDA OLAN HAZIR BIR METHOD--> Character



        File file=new File("src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/KendiPostmanApimiz/getRequest/workspaces.json");

        JsonPath jsonPath=JsonPath.from(file);

        Map worksapce01=jsonPath.getMap("workspace.collections[0]");
        System.out.println(worksapce01);

        System.out.println((Object)jsonPath.getJsonObject("workspace"));
        System.out.println("---------------------------------------------------------");
        System.out.println((Object)jsonPath.getJsonObject("workspace.collections[2]"));
    }

}
