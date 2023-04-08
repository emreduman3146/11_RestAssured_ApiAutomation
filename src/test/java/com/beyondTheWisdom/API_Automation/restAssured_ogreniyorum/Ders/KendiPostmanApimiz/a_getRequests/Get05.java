package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.a_getRequests;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get05 extends TestBase {

    @Test
    public void hamcrest_librarydeki_farkli_methodlari_pratik_edelim()
    {
        Response response=
            given().
                    baseUri("https://api.getpostman.com/").
                    basePath("workspaces/").
                    //pathParam("workspaceid","4bed1ef1-11ef-4abe-875b-0ab979fb80ac").
                    header("X-API-Key", "PMAK-6427aaa996462a5cfa5e494d-84d0bf3654a81e1ae7f1372487c80c588c").
            when().
                    get().
            then().
                assertThat().
                    statusCode(HttpStatus.SC_OK).
                    body(
                         "workspaces.name", contains("My Workspace","PostmanTraining","Postman_YeniProject"),
                    "workspaces.name", containsInAnyOrder("PostmanTraining","My Workspace","Postman_YeniProject"),
                            "workspaces.name", hasItems("My Workspace","PostmanTraining"),
                            "workspaces.name", hasItem("My Workspace"),
                            "workspaces.name", not(hasItem("My Workspaceeeeee")),

                            "workspaces[0].name", equalTo("My Workspace"),
                            "workspaces[1].type",is(equalTo("team")), //okunabilirlik olsun diye
                            //"workspaces[0].name", empty(),//collection ya da herhangi bor data yapisi icin kullanilabilir
                            "workspaces[0].name", is(not(empty())),
                            "workspaces[0].name", is(not(emptyString())),
                            "workspaces[0].name", is(not(emptyOrNullString())),

                            "workspaces.name", hasSize(3),
                            "workspaces.size()",equalTo(3),

                            //"workspaces.name", everyItem(startsWith("P"))
                            "workspaces[2]", hasKey("visibility"),
                            "workspaces[2]", hasValue("team"),
                            "workspaces[2]", hasEntry("visibility","team"),
                            "workspaces[2]", not(equalTo(Collections.EMPTY_MAP)),


                            "workspaces[2].name", allOf(startsWith("P"),containsString("Project")),
                            "workspaces[2].name", anyOf(startsWith("P"),containsString("Workspace"))


                            //INTEGER VALUE'LAR ICINSE
                            //     greaterThan();
                            //     greaterThanOrEqualTo();
                            //     lessThan();
                            //     lessThanOrEqualTo();


                            //Bir java programlamaci yein bir library ile calisirken ne yapar?
                            //Library'yi kesfedebilmek icin JAVA DOCUMENTATION okuyabilir.
                            //Google'a "Hamcrest javadoc" yazarsak class/methodlari bulabiliriz.



                            ).
                extract().
                    response().
                    prettyPeek();

    }


}

