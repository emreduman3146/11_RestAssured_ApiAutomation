package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Herokuapp_Testing.a_getRequests;

import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Get02 {

    /*
    Positive Scenario
    when() a getRequest is sent to  given end-point
    "https://restful-booker.herokuapp.com/booking"

    And() Accept Type is "application/json" Request HEADER
    then() statusCode is 200
    and content-type is "application/json"  Response HEADER
    */

    @Test
    public void getRequest01()
    {
        ContentType contentType= ContentType.JSON;

        given().
            accept("application/json").//json format'ında data kllanmak istiyorum
            accept(contentType).
            accept(ContentType.JSON).
        when().//http request methods kullanılır when()'den sonra
            get("https://restful-booker.herokuapp.com/booking").
        then().
            assertThat().
                statusCode(200).
                contentType("application/json");
    }

    /*
   Negative Scenario
   when() a getRequest is sent to  given end-point
   "https://restful-booker.herokuapp.com/booking/101"

   And() Accept Type is "application/json"
   then() statusCode is 404 ise content type yoktur

   */
    @Test
    public void getRequest02()
    {
        //getting data
        Response response =
                given().
                    accept("application/json").
                when().
                    get("https://restful-booker.herokuapp.com/booking/477777777777");



        //printing
        //response.prettyPrint();

        ResponseBody responseBody=response.body();
        responseBody.prettyPrint();

        //JAVADA ATANMAMAIS/ASSIGN EDILMEMIS OBJELER HEAP BELLEKTEN GARBAGE COLLECTOR TARAFINDAN TEMIZLENIR
        response.body().print();

        System.out.println("=================================================");
        response.body().peek();
        System.out.println("=================================================");
        response.body().prettyPeek();


        System.out.println("=================================================");
        response.body().prettyPeek().print();
        System.out.println("=================================================");
        response.body().prettyPeek().prettyPrint();

        //assertion
        response.
        then().
            assertThat().
                statusCode(404).//Not Found
                contentType(ContentType.JSON);//data yoka content type ına bakılamaz
    }

    /*
     Negative Scenario
     when() a getRequest is sent to  given end-point
     "https://restful-booker.herokuapp.com/booking/1001"

     And() Accept Type is "application/json"
     then() statusCode is 404
     and() response body is "Not Found"

     */

    @Test
    public void getRequest03()
    {
        Response response=
                given().
                  accept("application/json").
                when().
                    get("https://restful-booker.herokuapp.com/booking/105401");

        response.prettyPrint();//Github'a push edilirken silinir




        //TESTNG 7.7.1
        //assertEquals(response.getStatusCode(),404);
        //assertTrue(response.asString().contains("Not Found"));
        //asString() converting into String object

        //RESTASSURED LIB Assertion Teknigi
        response.then().assertThat().statusCode(404);
    }

}
