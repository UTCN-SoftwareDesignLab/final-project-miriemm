package com.finalproject.flight;

import com.finalproject.flight.model.Flight;
import com.finalproject.flight.model.dto.FlightDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightDTO toDto(Flight flight);

    Flight fromDTO(FlightDTO flight);
}
