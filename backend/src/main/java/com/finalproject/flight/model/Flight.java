package com.finalproject.flight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer flightNr;

    @Column(length = 256)
    private String departurePlace;

    @Column(length = 255)
    private String destinationPlace;

    @Column
    private String date;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private Integer price;

    @Column
    private Integer seatNumbers;


}
