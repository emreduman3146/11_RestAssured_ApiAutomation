package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.util;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo.BookingRequestPayload;
import static  com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.e_patchRequests.objects.Objects.*;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;


public class ReusableMethods
{
    public static BookingRequestPayload createBookingRequestPayloadWithFaker() {

        BookingDates bookingDates = BookingDates.builder()
                .checkin(faker.date().future(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString())
                .checkout(faker.date().future(20, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString())
                .build();

        return BookingRequestPayload.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(500, 2000))
                .depositpaid(faker.bool().bool())
                .bookingdates(bookingDates)
                .additionalneeds(faker.lorem().sentence())
                .build();
    }






}
