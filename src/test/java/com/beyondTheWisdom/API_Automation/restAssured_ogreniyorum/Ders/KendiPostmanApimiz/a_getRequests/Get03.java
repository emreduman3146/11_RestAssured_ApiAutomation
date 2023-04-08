package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.a_getRequests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03
{

    @Test
    public void groovyDili_MatchersClassi_ile_responseBody_validateEtmek()
    {
        Response response=
            given().
                baseUri("https://api.getpostman.com/").
                basePath("workspaces/").
                header("X-API-Key", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
            when().
                get();

        response.prettyPrint();

        response.
            then().
                assertThat().
                    statusCode(HttpStatus.SC_OK).
                    body(
                         "workspaces.name", Matchers.hasItems("My Workspace","PostmanTraining","Postman_YeniProject"),
                    "workspaces.name", Matchers.hasItem("My Workspace"),
                            "workspaces[0].name", equalTo("My Workspace"),
                            "workspaces[1].type",is(equalTo("team")), //okunabilirlik olsun diye
                            "workspaces.size()",equalTo(3)
                    );

    }



}
