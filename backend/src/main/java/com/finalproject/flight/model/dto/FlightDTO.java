package com.finalproject.flight.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private Long id;
    private Integer flightNr;
    private String departurePlace;
    private String destinationPlace;
    private String date;
    private String startTime;
    private String endTime;
    private Integer price;
    private Integer seatNumbers;

}
