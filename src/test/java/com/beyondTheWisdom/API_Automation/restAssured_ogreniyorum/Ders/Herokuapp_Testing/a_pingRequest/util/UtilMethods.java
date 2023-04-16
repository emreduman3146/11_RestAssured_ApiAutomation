package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.util;

import io.restassured.response.Response;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.constants.ApiEndpoints.BASE_URL;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.a_pingRequest.constants.ApiEndpoints.PING_ENDPOINT;
import static io.restassured.RestAssured.get;

public class UtilMethods
{
    public static Response healthCheck()
    {
        return get(BASE_URL+PING_ENDPOINT);
    }

}
