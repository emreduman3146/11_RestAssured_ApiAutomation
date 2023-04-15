package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

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

    @JsonProperty("bookingid")
    private int bookingid;
    @JsonProperty("booking")
    private BookingRequestPayload bookingRequestPayload;

}