package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.util.UtilMethods.healthCheck;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Ping01
{
     //A simple health check endpoint to confirm whether the API is up and running.
    @Test
    void health_check_testing_via_ping__AND__assert_if_statusCode_201() {
        Response response = healthCheck();
        assertThat(response.statusCode(), equalTo(SC_CREATED));

    }

}




