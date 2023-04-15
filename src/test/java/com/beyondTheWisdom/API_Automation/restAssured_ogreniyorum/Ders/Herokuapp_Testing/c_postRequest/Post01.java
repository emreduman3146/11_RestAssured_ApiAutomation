package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Post01 extends TestBase
{

    /*
    Post Request icin;
        end-point sart
        request Body sart
        authorization sart(BAZI MOCK APILER ICIN OLMAYABILIR)
        accept type istege baglıdır
        content type istege baglıdır

    NOTE:
        API developerlar olusturulacak datanın bazı bolumlerının
        NOT NULL, UNIQUE olması durumunda, post request olusturulurken
        kesinlikle o kısımlara uygun deger verilerek POST Request olusturulur.
        SQL'de CONSTRAINT GIBI DUSUN  PRIMARY KEY NOT NULL BOS OLAMAZ

        YOKSA, 400 Bad Request http status code alırız
     */

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
    @Test
    public void hardCoded_stringFormatinda_jsonDatamiz_var__AND__bununla_postRequest_atalim()
    {
        //1.Way - not good

        String jsonRequestBody="{\n" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Brown\",\n" +
                "\"totalprice\": 383,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2018-11-30\",\n" +
                "\"checkout\": \"2019-09-11\"\n" +
                "},\n" +
                "\"additionalneeds\":\"Wifi\"\n" +
                "}";





        //requestSpecification01.pathParam("bookingid",5);' e post yapamayız put patch yapabiliriz
        //post yaparken kullanılmayan id atanır request body'e


        Response response=
                given().
                        contentType(ContentType.JSON).
                        spec(requestSpecification01).
                        basePath("booking").
                        body(jsonRequestBody)
                        .when()
                        .post();

        response.prettyPrint();

        //yollanan request body yi görmek istiyoruz
        response.prettyPrint();

        //ASSERTION
        response.
            then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON);


        //json datayi jsonPath objesine atayalim ki json icerisinde gezip isstedigimiz datayi getirebilelim
        JsonPath jsonPath = response.jsonPath();

        //ASSERTION BASLIYOR
        //testng--> hardassertion-softassertion
        SoftAssert softAssert=new SoftAssert();

        //verify datas
        softAssert.assertEquals(jsonPath.getString("booking.firstname"),"Sally");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),"Brown");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),383);
        softAssert.assertEquals(jsonPath.getString("booking.totalprice"),"383");//üstteki ile aynı isi görür
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),"2018-11-30");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),"2019-09-11");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),"Wifi");

        softAssert.assertAll();//yapmis oldugun tum soft assertionlarin sonuclarini ban goster---> 9pass 1 fail




    }


}
