package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.getRequest;

public class MethodZincirlemeMantigi
{

    static MethodZincirlemeMantigi obje =new MethodZincirlemeMantigi();

    public static void main(String[] args)
    {
        obje.method01();
        obje.method02();
        obje.method03();


       given().when().then();

    }



    public void method01()
    {
        System.out.println("01");
    }
    public void method02()
    {
        System.out.println("02");
    }
    public void method03()
    {
        System.out.println("03");
    }

    public static MethodZincirlemeMantigi given()
    {
        System.out.println("04");
        return obje;
    }

    public MethodZincirlemeMantigi when()
    {
        System.out.println("05");
        return obje;

    }
    public MethodZincirlemeMantigi then()
    {
        System.out.println("06");
        return obje;

    }

}
