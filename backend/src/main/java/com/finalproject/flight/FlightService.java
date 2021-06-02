package com.finalproject.flight;


import com.finalproject.flight.model.Flight;
import com.finalproject.flight.model.dto.FlightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    private Flight findById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found: " + id));
    }

    public List<FlightDTO> findAll() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    public FlightDTO create(FlightDTO flight) {
        return flightMapper.toDto(flightRepository.save(
                flightMapper.fromDTO(flight)
        ));
    }

    public FlightDTO edit(FlightDTO flight) {
        Flight actFlight = findById(flight.getId());
        actFlight.setDeparturePlace(flight.getDeparturePlace());
        actFlight.setDestinationPlace(flight.getDestinationPlace());
        actFlight.setStartTime(flight.getStartTime());
        actFlight.setEndTime(flight.getEndTime());
        actFlight.setPrice(flight.getPrice());
        actFlight.setSeatNumbers(flight.getSeatNumbers());


        return flightMapper.toDto(
                flightRepository.save(actFlight)
        );
    }

    public void delete(Long id) {
        Flight flightToDelete = findById(id);
        flightRepository.delete(flightToDelete);
    }

    // method to search for a flight that has a specific departure place, destination place and date
    public List<FlightDTO> findByMultipleCriteria(String departurePlace, String destinationPlace, String date){
        return flightRepository.findAll(Specification.where(FlightSpecifications.departurePlaceContains(departurePlace)).and(FlightSpecifications.destinationPlaceContains(destinationPlace)).and(FlightSpecifications.dateContains(date))).stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

}
