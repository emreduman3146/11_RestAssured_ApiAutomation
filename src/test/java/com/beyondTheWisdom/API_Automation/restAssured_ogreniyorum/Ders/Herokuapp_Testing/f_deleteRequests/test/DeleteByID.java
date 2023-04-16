package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.test;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.testbase.TestBase;
import io.restassured.response.Response;
import org.openqa.selenium.remote.CommandPayload;
import org.testng.annotations.*;

import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.objects.Objects.pathParam;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.objects.Objects.token;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.constants.ResponsePayloadMessages.CREATED;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.HttpRequestsUtil.deleteBooking;
import static com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util.PropertiesReadWrite.getProperty;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteByID extends TestBase
{
    //mvn clean test -Dgroups="ByID"
    //mvn clean test -DsuiteXmlFile=testngSuiteRunner.xml
    //mvn clean test -Dtest=DeleteByID


    @Test(groups = {"ByID"})
    public void test01_deleteRequest_deleteBooking_byID_returns201_Created()
    {
        //DELETE HTTPS REQUEST BY ID
        //STORE THE RESPONSE DETAILS
        Response response = deleteBooking(getProperty(pathParam), token);

        //DO ASSERTION VIA HAMCREST(Matchers Class)
        //STATUS CODE + RESPONSE BODY
        assertThat(response.statusCode(), equalTo(SC_CREATED));//org.apache.http.HttpStatus
        assertThat(response.getBody().asPrettyString(), equalTo(CREATED));

        response.prettyPrint();

    }




}
