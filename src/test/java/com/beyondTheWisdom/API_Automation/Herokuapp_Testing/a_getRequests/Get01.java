package com.beyondTheWisdom.API_Automation.Herokuapp_Testing.a_getRequests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get01
{
    //By using restAssured library, I'll do API automation Testing.

    /*
    given, and, then, when : gherkin language
    restassured library de gherkin languagedeki ifadeleri kullanır
     */
    @Test
    public void getRequest01()
    {

        given().
                when().//after when(), use END POINT, http request methods kullanılır when()'den sonra
                get("https://restful-booker.herokuapp.com").//END POINT -get request in postman
                then().//after then(), do assertion
                assertThat().
                statusCode(200);
        //assertThat if the status code is 200!!!


        given().
                when().
                get("https://restful-booker.herokuapp.com/booking/3").
                prettyPrint();

    }

    @Test
    public void getRequest02(){
        Response response =given().
                when().
                get("https://restful-booker.herokuapp.com/booking/3");
        //I put the data into response object.
        //the type of retrieved data by using end-point is response type

        response.prettyPrint(); // I print out the data into response
        //to write response body in console.

        //to print out status code
        System.out.println(response.getStatusCode());
        //200

        //IN POSTMAN, IN STATUS LINE,there are (STATUS-TIME-SIZE)
        System.out.println(response.statusLine());
        //HTTP/1.1 200 OK

        //Content-Type of data in Response Body
        System.out.println(response.getContentType());//1.way
        System.out.println(response.getHeader("getContentType"));//2.way
        //application/json; charset=utf-8

        System.out.println(response.getBody());
        //io.restassured.internal.RestAssuredResponseImpl@6c6357f9

        System.out.println(response.getBody().print());

        System.out.println(response.getBody().asString());
        //System.out.println(response.prettyPrint()); ile aynı result 'ı verir

        //IN POSTMAN, There is "headers" section of RESPONSE BODY
        System.out.println(response.getHeaders());//to get all infos in headers section
        /*
        Server=Cowboy
        Connection=keep-alive
        X-Powered-By=Express
        Content-Type=application/json; charset=utf-8
        Content-Length=196
        Etag=W/"c4-n+fEK7399yC/YOjqgPFYxCcTwC0"
        Date=Tue, 02 Feb 2021 19:57:12 GMT
        Via=1.1 vegur
         */

        //to get specific data in headers of response body
        System.out.println(response.getHeader("Server"));
        //Cowboy

        //ASSERTION OF STATUS CODE
        response.
                then().//assertion için then() kullanıyourz
                assertThat().//asserThat=hard assertion(yani 1 hatada test fail olur )
                // - assertThat()'den sonra farklı seyleri assert edebiliriz
                        statusCode(200).// statusCode assertion
                contentType("application/json; charset=utf-8");//content-type assertion

    }

}
