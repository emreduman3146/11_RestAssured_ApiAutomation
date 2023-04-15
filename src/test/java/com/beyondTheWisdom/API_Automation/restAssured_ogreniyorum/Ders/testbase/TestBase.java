package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;


public class TestBase
{
    //  BASE URL imizi koyacagiz ki ihityaci olan alip
    //spec() isimli methodun icine parametre olarak koysun
    public static RequestSpecification requestSpecification01,requestSpecification02,requestSpecification03;//null



    @BeforeTest
    public void setUp01()
    {


        requestSpecification01=new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com/").//BASE URL
                build();

        requestSpecification02=new RequestSpecBuilder().
                setBaseUri("").//BASE URL
                build();

        requestSpecification03=new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();

    }



}
