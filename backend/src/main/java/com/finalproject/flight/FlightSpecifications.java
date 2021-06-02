package com.finalproject.flight;

import com.finalproject.flight.model.Flight;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecifications {
    static Specification<Flight> departurePlaceContains(String departurePlace){
        return (flight,cq,cb) -> cb.like(flight.get("departurePlace"), "%" + departurePlace + "%");

    }

    static Specification<Flight> destinationPlaceContains(String destinationPlace){
        return (flight,cq,cb) -> cb.like(flight.get("destinationPlace"), "%" + destinationPlace + "%");
    }

    static Specification<Flight> dateContains(String date){
        return (flight,cq,cb) -> cb.like(flight.get("date"), "%" + date + "%");

    }
}
