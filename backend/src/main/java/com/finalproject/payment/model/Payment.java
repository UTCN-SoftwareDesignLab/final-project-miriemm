package com.finalproject.payment.model;

import com.finalproject.flight.model.Flight;
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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256)
    private String firstName;

    @Column(length = 256)
    private String lastName;


    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
