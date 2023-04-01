package com.beyondTheWisdom.API_Automation.Herokuapp_Testing.testbase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;


public class TestBase {

    protected RequestSpecification requestSpecification01;


    @BeforeTest
    public void setUp01(){
        requestSpecification01=new RequestSpecBuilder().
                                setBaseUri("https://restful-booker.herokuapp.com/").//BASE URL
                                build();
        //requestSpecification01 object'in içinde END-POINT'im var
        //FOR TESTS, I SET UP BASE URL
    }


}
