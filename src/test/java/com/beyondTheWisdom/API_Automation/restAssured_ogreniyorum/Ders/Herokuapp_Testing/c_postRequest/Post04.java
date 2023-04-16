package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.c_postRequest;


import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingResponsePayload;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.testbase.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Post04 extends TestBase //TestNG @Before ya da @After methodlari calistirilsin diye extends ediyoruz.
{

    //JAVA KONUSU
    //Variableiniz class seviyesinde ise(instance variable)  initializing zorunlu degildir
    //ornegin
    String isim;

    @Test
    public void javaFakerLibrary_ve_pojoClassLombokBuilderIleOlusturulmasi_postBookingRequestyapma__AND__JacksonLibraryile_serialization_ve_deserialization_yapma()
    {

        //AUTOMATION STEPLERI
            //STEP1->bookingRequestPayload Objesini LOMBOK @Builder araciligiyla olusturalim.
            BookingRequestPayload bookingRequestPayload=createBookingRequestPayloadWithJavaFaker();
            System.out.println(bookingRequestPayload);

            //STEP2
            //JAVA OBJECTEN --> JSON DATASINI URETME ISLEMINE SERIALIZATION DENIR
            //BU ISLEMI YAPABILMEK ICIN DE JACKSON LIBRARY KULLANACAGIZ
            String jsonRequestBody=null;//String variable declaration
            try {
                 jsonRequestBody=new ObjectMapper().writeValueAsString(bookingRequestPayload);//initializing
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //JAVA KONUSU
            //Variableiniz method seviyesinde ise(local variable)  initializing zorunludur
            System.out.println(jsonRequestBody);

            //STEP3->HTTP POST REQUEST OLUSTURALIM.
            Response response=given().
                                contentType(ContentType.JSON).
                                spec(requestSpecification01).//https://restful-booker.herokuapp.com/
                                body(jsonRequestBody).
                            when().
                                post("/booking");

            response.prettyPrint();

        //STEP4
        //DESERIALIZATION
        //JSON FORMATINDAKI DATAYI---> JAVA POJO CLASS OBJECT FORMATINA DONUSTURMEK ISTIYORUZ
        BookingResponsePayload bookingResponsePayload=null;
        try {
             bookingResponsePayload=new ObjectMapper().readValue(response.asString(), BookingResponsePayload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(bookingRequestPayload);

        //FATIH,TAHTAKALE IMALATHANE 100 TISORT URETTINIZ  1000$
        //YURICI KARGONUN DEPOSUNA GONDER  (200$)  -siparisi hep komtrol et- bankayi kontrol - kargocuyu ara siparis geldi su su adrese gonder

        //BUTIK DUKKANIMVAR ELBISE SATARIM
        //KENDI WEBSITEM VAR(GIRIS-SATIN AL ODEME)


        //TRENDYOL API(tisort,kirmizi,medium,pamuk)-satis-odeme-stok takip-sikayet-iade-depo yonetimi(100$)
        //TRENYOL DATABASE INE  MICROSERVICE KANALLARI ARACILIGIYLA URUN YUKLEDIK-POST
        //DB-> GET REQUEST(su anlik Post04 classinda get request calismasi yapmiyoruz-cunku sadece post requeste odaklandik)


        //TESTING STEPLERI
            //STEP1
            SoftAssert softAssert=new SoftAssert();
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getFirstname(),bookingRequestPayload.getFirstname());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getLastname(),bookingRequestPayload.getLastname());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getTotalprice(),bookingRequestPayload.getTotalprice());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getDepositpaid(),bookingRequestPayload.getDepositpaid());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getBookingdates().getCheckin(),bookingRequestPayload.getBookingdates().getCheckin());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getBookingdates().getCheckout(),bookingRequestPayload.getBookingdates().getCheckout());//--->TRUE/PASS
            softAssert.assertEquals(bookingResponsePayload.getBookingRequestPayload().getAdditionalneeds(),bookingRequestPayload.getAdditionalneeds());//--->TRUE/PASS

            softAssert.assertAll();


    }

    //UTIL METHOD
    BookingRequestPayload createBookingRequestPayloadWithJavaFaker()
    {
        //Bir Faker objesi olusturduk.-Cunku rastgele degerler kullanmak istedigimiz icin
        Faker faker=new Faker();

        String checkin=faker.date().future(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        String checkout=faker.date().future(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).plusDays(10L).toLocalDate().toString();


        //JAVA OBJECTI OLUSTURUYORUZ. NEW ILE YAPMAYACAGIZ. LOMBOK BUILDER ILE YAPACAGIZ.
        BookingRequestPayload bookingRequestPayload=BookingRequestPayload.builder().
                                                    firstname(faker.name().firstName()).
                                                    lastname(faker.name().lastName()).
                                                    totalprice(faker.number().numberBetween(200,400)).
                                                    depositpaid(faker.bool().bool()).
                                                    bookingdates(BookingDates.builder().
                                                                    checkin(checkin).
                                                                    checkout(checkout).
                                                                    build()).
                                                    additionalneeds("Wifi").
                                                    build();

        return bookingRequestPayload;
    }
}


//20 GECE 3. DERS BASLAYACAK!!!