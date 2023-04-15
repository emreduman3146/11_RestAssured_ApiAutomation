package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.b_getRequests;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class Get03
{

    /*
    positive Scenario:
    When I send a GET REQUEST to REST API END POINT
    "https://restful-booker.herokuapp.com/booking/1"
    and Accept-type is "application/json"
    then
    HTTP Status-Code is 200
    and Response Content-type is "application/json"
         {
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
     */
    @Test
    public void bodyMethodu_ile_responseBody_validateEtmek__AND__matchersClassiKullanimi()
    {
        Response response=
                given().
                     accept("application/json").
                when().//http request methods kullanılır when()'den sonra
                    get("https://restful-booker.herokuapp.com/booking/1");


        response.prettyPrint();


        response.
            then().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("James")).
                body("lastname", Matchers.equalTo("Brown")).
                body("totalprice", Matchers.equalTo(111)).
                body("depositpaid", Matchers.equalTo(true)).
                body("bookingdates.checkin", Matchers.equalTo("2018-01-01")).
                body("bookingdates.checkout", Matchers.equalTo("2019-01-01")).
                body("additionalneeds",Matchers.equalTo("Breakfast"));


        //not to repeat body() method again and again
        ValidatableResponse validatableResponse=
                response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType("application/json");

        validatableResponse.
                body("firstname", Matchers.equalTo("James"),
                "lastname", Matchers.equalTo("Brown"),
                        "totalprice", Matchers.equalTo(111),
                        "depositpaid", Matchers.equalTo(true),
                        "bookingdates.checkin", Matchers.equalTo("2018-01-01"),
                        "bookingdates.checkout", Matchers.equalTo("2019-01-01"),
                        "additionalneeds",Matchers.equalTo("Breakfast"));




        //not to repeat body() method again and again
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("James"),
                        "lastname", equalTo("Brown"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));



    }
}
