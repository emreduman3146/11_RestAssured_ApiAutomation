package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.util;

import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.objects.Objects;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.BookingDates;
import com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.f_deleteRequests.pojo.BookingRequestPayload;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PayloadCreationUtil
{

    public static BookingRequestPayload createBookingRequestPayload()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfDate.format(new Date());

        return BookingRequestPayload.builder()
                .firstname(Objects.faker.name().firstName())
                .lastname(Objects.faker.name().lastName())
                .totalprice(Objects.faker.number().numberBetween(100, 500))
                .depositpaid(true)
                .bookingdates(
                        BookingDates.builder().checkin(currentDate).checkout(currentDate).build())
                .additionalneeds("Breakfast")
                .build();
    }



}
