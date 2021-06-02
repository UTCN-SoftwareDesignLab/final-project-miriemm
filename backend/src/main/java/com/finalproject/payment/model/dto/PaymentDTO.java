package com.finalproject.payment.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer flightNr;
    private Integer price;

}
