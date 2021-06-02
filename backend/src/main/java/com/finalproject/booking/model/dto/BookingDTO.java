package com.finalproject.booking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer flightNr;
    private Integer price;
}
