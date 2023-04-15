package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Post03 extends TestBase {

  /*
    POST SCENARIO:
    AcceptType : json
    when I post a request
    "https://restful-booker.herokuapp.com/"
    request body:

         {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 383,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-11-30",
                "checkout": "2019-09-11"
            },
            "additionalneeds":"Wifi"
        }

        Then status code:200
        response body should match with request body

     */


    // POJO: PLAIN OLD JAVA OBJECT

    @Test
    public void pojo_classlari_ile_javaObjectTipinde_jsonData_olusturalim__AND_postRequest_atalim()
    {

        //JSON FORMATINDA DATA OLUSTURALIM
        //BUNU DA POJO CLASSSLARI YARDIMI ILE YAPALIM
        BookingDates bookingdates=new BookingDates("2018-11-30","2019-09-11");

        BookingRequestPayload booking=new BookingRequestPayload("Sally","Brown",383,true,bookingdates,"Wifi");



        Response response= given().
                contentType(ContentType.JSON).
                spec(requestSpecification01).
                body(booking).
                when().
                post("booking");

        response.
            then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON);

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        //verify datas
        softAssert.assertEquals(jsonPath.getString("booking.firstname"), booking.getFirstname());
        softAssert.assertEquals(jsonPath.getString("booking.lastname"), booking.getLastname());

        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"), (int)booking.getTotalprice());
        //Integer wrapper class object return eder-ya (int)ile conversion yaparım
        // ya da Integer değil int return ettiririm
        //yada jsonPath.get(); yaparım, get() tüm primitive datalara uyar
        softAssert.assertEquals(jsonPath.getString("booking.totalprice"),booking.getTotalprice().toString());//üstteki ile aynı isi görür
        softAssert.assertEquals(jsonPath.get("booking.totalprice"),booking.getTotalprice());//üstteki ile aynı isi görür

        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),(boolean)booking.getDepositpaid());//Boolean wrapper class object return eder
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),booking.getBookingdates().getCheckin());
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),booking.getBookingdates().getCheckout());
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"), booking.getAdditionalneeds());

        softAssert.assertAll();




    }


}
