package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Herokuapp_Testing.a_getRequests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get01
{
    //By using restAssured library, I'll do API automation Testing.

    /*
    given, and, then, when : gherkin language
    restassured library de gherkin languagedeki ifadeleri kullanır
     */

    //JIRA Uygulamasindaki test Scenario->
    // https://restful-booker.herokuapp.com endPointine get request at
    // ve status code:200 mu verify et, body json olmali

    @Test
    public void getRequest01()
    {

        given().//pre-conditionlarimiz varsa given()'dan sonra ekleriz
                when().//action'in yapildigi az
                get("https://restful-booker.herokuapp.com").//get Request
                then().//verification kismi
                statusCode(200);//verify edilen detail



        given().
                when().//after when(), use END POINT, http request methods kullanılır when()'den sonra
                get("https://restful-booker.herokuapp.com").//END POINT -get request in postman
                then().//after then(), do assertion
                assertThat().
                statusCode(200);
        //assertThat if the status code is 200!!!


        String responseBody_Pretty=
                given().
                when().
                get("https://restful-booker.herokuapp.com/booking").prettyPrint();

    }

    public Response herokuApp_getRequest01()
    {
        return given().
                when().
                get("https://restful-booker.herokuapp.com/booking/3");

    }

    @Test
    public void getRequest02(){

        Response response=
                given().
                when().
                get("https://restful-booker.herokuapp.com/booking/3");

        response.prettyPrint();
        //I put the data into response object.
        //the type of retrieved data by using end-point is response type


        Response response01=herokuApp_getRequest01();
        response01.prettyPrint();

        System.out.println(response01.getStatusCode());//200


        Headers header01=response01.getHeaders();
        System.out.println(header01);


        //IN POSTMAN, IN STATUS LINE,there are (STATUS-TIME-SIZE)
        System.out.println(response01.statusLine());
        //HTTP/1.1 200 OK

        //Content-Type of data in Response Body
        System.out.println(response01.getContentType());//1.way
        System.out.println(header01.get("Content-Type"));//2.way
        System.out.println(response01.getHeader("Content-Type"));//3.way
        //application/json; charset=utf-8

        //to get specific data in headers of response body
        System.out.println(response01.getHeader("Server"));
        //Cowboy



        System.out.println(response01.getBody());
        //io.restassured.internal.RestAssuredResponseImpl@6c6357f9

        String body=response01.getBody().print();

        String body01=response01.getBody().asString();
        System.out.println(body01);
        String prettyBody=response01.prettyPrint(); //ile aynı result 'ı verir



        //ASSERTION OF STATUS CODE
        ValidatableResponse validatableResponse01=response01.then();//assertion için then() kullanıyourz


        validatableResponse01.
                assertThat().//asserThat=hard assertion(yani 1 hatada test fail olur )
                // - assertThat()'den sonra farklı seyleri assert edebiliriz
                        statusCode(200).// statusCode assertion
                        contentType("application/json; charset=utf-8");//content-type assertion

    }

}
