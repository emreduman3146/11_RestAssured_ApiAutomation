package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_putRequests;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBase {

    @Test
    public void putRequest01()
    {
        //retrieve the json data
        Response response=given().
                            spec(requestSpecification03).//"https://jsonplaceholder.typicode.com/todos"
                            accept(ContentType.JSON).
                          when().
                            get("/200");

        //printing out
        response.prettyPrint();
        /*
        {
            "userId": 10,
            "id": 200,
            "title": "ipsam aperiam voluptates qui",
            "completed": false
        }
         */

        //put() kullanılırken tüm veriler update edilir
        //JsonObject jsonObject=new JsonObject(); bunu kullanma
        JSONObject jsonObject=new JSONObject();//bunu kullan
        jsonObject.put("userId",11);
        jsonObject.put("id","201");
        jsonObject.put("title","emre");
        jsonObject.put("completed",true);

        System.out.println(jsonObject.toString());
        //{"id":"201","completed":true,"title":"emre","userId":11}

        Response responseAfterPut=given().
                                    contentType(ContentType.JSON).
                                //contentType'ın tastiklenmesi şarttır!!!
                                    spec(requestSpecification03).
                                    //auth().basic("admin","password123").
                                    body(jsonObject.toString()).
                                    when().
                                    put("/200");

        responseAfterPut.prettyPrint();
        /*
        {
            "id": 200,  ***id değismedi, swagger documentation'da datanın içeriği yazar,özelliği
            "completed": true,
            "title": "emre",
            "userId": 11
        }
         */


        given().
            accept(ContentType.JSON).
            spec(requestSpecification03).
        when().
            get("/200").
                prettyPrint();
        /*
        {
            "userId": 10,
            "id": 200,
            "title": "ipsam aperiam voluptates qui",
            "completed": false
        }
         */


    }
}
