package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "checkin",
        "checkout"
})

/**
 * No args constructor for use in serialization
 *
 */
public class BookingDates
{

    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;

}


