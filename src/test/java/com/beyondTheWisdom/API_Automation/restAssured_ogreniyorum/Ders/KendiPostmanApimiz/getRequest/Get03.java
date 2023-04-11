package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.getRequest;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import java.util.Collections;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class Get03
{


    /**
     *
     */
    @Test
    public void hamcrestLibrarydeki_farkli_methodlarini_pratik_edelim__AND__groovyDiliKullanalim()
    {
        //GECEN HAFTA GORDUGUMUZ HAMCREST LIBRARY'YI DETAYLICA ISLEYELIM.

        //AUTOMATION - RESTASSURED ILE  HTTP GET REQUEST AUTOMATION
        Response response=RestAssured.
            given().
                baseUri("https://api.getpostman.com").
                basePath("/workspaces").
                //pathParam("workspaceid", "4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                header("X-API-Key","PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
                header("Accept-Encoding","gzip, deflate, br" ).
                accept(JSON).
            when().
                get();


     //AUTOMATION TESTING - VALIDATION
            response.
                then().
                    assertThat().
                    contentType(JSON).
                    statusCode(SC_OK).
                    body("workspaces.name", contains("My Workspace","PostmanTraining","Postman_YeniProject"),//sira onemli, tum elemanlar olmali
                    "workspaces.name", containsInAnyOrder("PostmanTraining","My Workspace","Postman_YeniProject"),

                            "workspaces.name", hasItem("PostmanTraining"),
                            "workspaces.name", hasItems("PostmanTraining","My Workspace"),
                            "workspaces.name", not(hasItems("PostmanTraining123456","My Workspace12345")),

                            "workspaces[0].visibility", equalTo("personal"),
                            "workspaces[0].visibility", is(equalTo("personal")),

                            //"workspaces", empty()//collectionin kendisi icin kullanilir, collection empty mi degil mi?
                            //"workspaces[0].visibility", emptyString()
                            "workspaces[0].visibility", is(not(emptyString())),
                            "workspaces[0].visibility", is(not(emptyOrNullString())),

                            "workspaces.name",hasSize(3),
                            "workspaces.size()",equalTo(3),

                            "workspaces[2]",hasKey("id"),
                            "workspaces[2]",hasValue("Postman_YeniProject"),
                            "workspaces[2]",hasEntry("name", "Postman_YeniProject"),//key-value cifti
                            "workspaces[2]",not(equalTo(Collections.EMPTY_MAP)),


                            "workspaces[2].type",allOf(startsWith("t"),containsString("ea")),
                            "workspaces[2].type",anyOf(startsWith("t"),containsString("1235676ea"))

                            //INTEGER VALUELAR ICINSE SU METHODLAR VARDIR,
                            //greaterThan();greaterThanOrEqualTo();lessThan();lessThanOrEqualTo()


                            );

    }
}
