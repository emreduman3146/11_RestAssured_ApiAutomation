package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.lang.reflect.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bookingid",
        "booking"
})
public class BookingResponsePayload
{

    @JsonProperty("bookingid")//json'daki adi
    private int bookingid;//javadaki adi

    @JsonProperty("booking")
    private BookingRequestPayload bookingRequestPayload;

    //JAVA INNER(NESTED) CLASS
    //MEMBER CLASS(NON STATIC)
    //STATIC CLASS
    //ANONYMOUS CLASS
    public static class Deserializer implements JsonDeserializer<BookingResponsePayload> {
        @Override
        public BookingResponsePayload deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            int bookingid = jsonObject.get("bookingid").getAsInt();
            JsonElement bookingElement = jsonObject.get("booking");
            BookingRequestPayload bookingRequestPayload = context.deserialize(bookingElement, BookingRequestPayload.class);
            return new BookingResponsePayload(bookingid, bookingRequestPayload);
        }
    }


}