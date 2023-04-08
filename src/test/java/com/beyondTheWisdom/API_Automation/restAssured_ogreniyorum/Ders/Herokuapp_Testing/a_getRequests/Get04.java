package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_getRequests;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get04 extends TestBase {

    //TestBase Class olusturup her teste olusturulan dataları
    //TestBase classa koyup aynı kodları tekrar tekrar yazmayacagız

    /*
 positive Scenario:
 When I send a GET REQUEST to REST API END POINT
 "https://restful-booker.herokuapp.com/booking/5"
 and Accept-type is "applicaiton/json"
 then
 HTTP Status-Code is 200
 and Response Fromat is "application/json"
 and firstname is "Eric"
 and lastname is "Ericsson"
 and "checkin": "2018-04-13",
 and "checkout": "2019-12-22"
  */

    @Test
    public void specMethodu_ile_requestSpecificationObjesi_kullanimi() {
        Response response = given().
                spec(requestSpecification01).//"https://restful-booker.herokuapp.com/"BASE URL
                when().
                get("booking/1");//pathparamlari yazdik sadece

        response.prettyPrint();

        response.
                then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("firstname",equalTo("Mark"),
                        "lastname",equalTo("Ericsson"),
                        "totalprice",equalTo(800),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2017-09-11"),
                        "bookingdates.checkout", Matchers.equalTo("2017-10-04"));

        //import org.hamcrest.Matchers;  -Matchers.equalTo()

        //import static io.restassured.RestAssured.*; - equalTo()
            // thanks to this import I dont need to type "Matchers"
            //Matchers Class'ındaki static methodları import et
    }
}

