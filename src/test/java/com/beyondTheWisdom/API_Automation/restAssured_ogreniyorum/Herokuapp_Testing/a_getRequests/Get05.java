package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Herokuapp_Testing.a_getRequests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get05 {

    /*
    positive Scenario:
    When I send a GET REQUEST to REST API END POINT
    "https://dummy.restapiexample.com/api/v1/employees"
    and Accept-type is "applicaiton/json"
    then
    HTTP Status-Code is 200
    and Response Format is "application/json"
    and employee number is 24
    and "" is one of employee
    and 21,61,23 should be among  the employees ages
     */

    @Test
    public void getRequest01()
    {
        Response response=given().
                             accept(ContentType.JSON).//accept("application/json").
                          when().
                             get("https://dummy.restapiexample.com/api/v1/employees");

        response.prettyPrint();

        response.
            then().
                assertThat().
                  statusCode(200).
                  contentType(ContentType.JSON).
                  body(
                          "data.id",Matchers.hasSize(24),//List'in size'ı 24 mü?
                    "data.employee_name",Matchers.hasItem("Tiger Nixon"),//checks if such employee_name exist
                            "data.employee_age",Matchers.hasItems(21,61,23),
                            "data.id[0]",Matchers.equalTo(1));
                            //hasItem(), hasSize() Listler için kullanılır


    }




    /*
    positive Scenario:
    When I send a GET REQUEST to REST API END POINT
    "https://restful-booker.herokuapp.com/booking/5"
    and Accept-type is "applicaiton/json"
    then
    HTTP Status-Code is 200
    and Response Fromat is "application/json"
    and firstname is "Susan"
    and lastname is "Ericsson"
    and "checkin": "2018-04-13",
    and "checkout": "2019-12-22"
     */
    @Test
    public void getRequest02()
    {
        Response response= given().
                when().
                get("https://restful-booker.herokuapp.com/booking/5");

        response.prettyPrint();

        response.
            then().
                assertThat().
                  statusCode(200).
                  contentType(ContentType.JSON).
                  body("firstname",Matchers.equalTo("Sally"),
                          //"firstname",Matchers.hasItem("Susan"), ERROR!!!!!!
                          "totalprice",Matchers.equalTo(404),
                          "bookingdates.checkin", Matchers.equalTo("2015-10-02"));
    }


}
