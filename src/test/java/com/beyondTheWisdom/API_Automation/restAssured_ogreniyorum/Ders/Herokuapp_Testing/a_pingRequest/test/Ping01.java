package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.test;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.constants.ApiEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.constants.Constants.CREATED_PING_BODY;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.util.UtilMethods.healthCheck;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Ping01
{

    //NOTE:PING REQUEST GENELLIKLE EGER TESTNG YA DA JUNIT KULLANILIYORSA @BEFORE Annotationlarindan birsinde tetbase classinda calistirilir
     //A simple health check endpoint to confirm whether the API is up and running.
    @Test
    void health_check_testing_via_ping__AND__assert_if_statusCode_201()
    {
        //AUTOMATION
            //STEP01
            //https://restful-booker.herokuapp.com/ping endPoint'ine Get Request yapsin + return et Response
            Response response=healthCheck();


        //TESTING
            //STEP01
            //Hamcrest Library'deki MatcherAssert Classtan geliyor
            assertThat(response.getStatusCode(), equalTo( SC_CREATED));
            assertThat(response.getBody().asString(), equalTo( CREATED_PING_BODY));



    }



}




