package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.a_getRequests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Get04
{

    @Test
    public void assertionYaptiktanSonra_uzerindeCalistigimizResponse_extractEtmek()
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
                         "workspaces.name", Matchers.hasItems("My Workspace","PostmanTraining","Postman_YeniProject"),
                    "workspaces.name", Matchers.hasItem("My Workspace"),
                            "workspaces[0].name", equalTo("My Workspace"),
                            "workspaces[1].type",is(equalTo("team")), //okunabilirlik olsun diye
                            "workspaces.size()",equalTo(3)
                    ).
                extract().//Normalde, then() methodundan sonra Response objesi return edemeyiz.
                    response();

        //response.prettyPrint();

        String extractedResponse=response.asPrettyString();
        System.out.println(extractedResponse);

        System.out.println(response.path("workspaces[0].name").toString());


    }

    @Test
    public void assertionYaptiktanSonra_uzerindeCalistigimizResponsedan_belliBirBolumuextractEtmek()
    {
        Object response=
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
                                "workspaces.name", Matchers.hasItems("My Workspace","PostmanTraining","Postman_YeniProject"),
                                "workspaces.name", Matchers.hasItem("My Workspace"),
                                "workspaces[0].name", equalTo("My Workspace"),
                                "workspaces[1].type",is(equalTo("team")), //okunabilirlik olsun diye
                                "workspaces.size()",equalTo(3)
                        ).
                    extract().//Normalde, then() methodundan sonra Response objesi return edemeyiz.
                        response().
                        path("workspaces.name");

        System.out.println(response);


    }


    @Test
    public void extract_sonrasi_jsonPath_Kullanimi()
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
                                "workspaces.name", Matchers.hasItems("My Workspace","PostmanTraining","Postman_YeniProject"),
                                "workspaces.name", Matchers.hasItem("My Workspace"),
                                "workspaces[0].name", equalTo("My Workspace"),
                                "workspaces[1].type",is(equalTo("team")), //okunabilirlik olsun diye
                                "workspaces.size()",equalTo(3)
                        ).
                    extract().//Normalde, then() methodundan sonra Response objesi return edemeyiz.
                        response();

        response.prettyPrint();

        System.out.println(response.path("workspaces[0].name").toString());

        JsonPath jsonPath=new JsonPath(response.asString());
        System.out.println(jsonPath.getString("workspaces[0].name"));
        System.out.println((String) jsonPath.get("workspaces[0].name"));
        System.out.println((List) jsonPath.get("workspaces.name"));
        System.out.println(jsonPath.getList("workspaces").get(0));


    }


    @Test
    public void extract_sonrasi_jsonPath_from_methodu_kullanimi()
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
                                "workspaces.name", Matchers.hasItems("My Workspace","PostmanTraining","Postman_YeniProject"),
                                "workspaces.name", Matchers.hasItem("My Workspace"),
                                "workspaces[0].name", equalTo("My Workspace"),
                                "workspaces[1].type",is(equalTo("team")), //okunabilirlik olsun diye
                                "workspaces.size()",equalTo(3)
                        ).
                        extract().//Normalde, then() methodundan sonra Response objesi return edemeyiz.
                        response();

        String jsonData=response.asString();

        JsonPath jsonPath=JsonPath.from(jsonData);

        System.out.println(jsonPath.getString("workspaces[0].name"));
        System.out.println((String) jsonPath.get("workspaces[0].name"));
        System.out.println(jsonPath.getList("workspaces").get(0));


    }


    @Test
    public void jsonPath_objesi_olusturma_from_methodu_kullanimi2() throws IOException
    {
        //JSON FILE ---> STRING OBJECT
        String filePath="src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/KendiPostmanApimiz/a_getRequests/personInfo.json";
        String json_TO_string = new String(Files.readAllBytes(Paths.get(filePath)));

        JsonPath jsonPath=JsonPath.from(json_TO_string);

        System.out.println(jsonPath.getString("name"));
        System.out.println(jsonPath.getInt("age"));
        System.out.println(jsonPath.getBoolean("isEmployed"));
        System.out.println((String)jsonPath.get("address.street"));
        System.out.println((String)jsonPath.get("projects[0].title"));



        System.out.println(jsonPath.getList("skills"));
        System.out.println((Map)jsonPath.get("pets[1]"));
        System.out.println((Map)jsonPath.getMap("pets[1]"));
        System.out.println((Object)jsonPath.getJsonObject("pets[1]"));
        System.out.println((Object) jsonPath.get());
        jsonPath.prettyPrint();


    }


}
