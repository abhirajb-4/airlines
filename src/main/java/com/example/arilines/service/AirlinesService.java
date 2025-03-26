package com.example.arilines.service;

import com.example.arilines.dto.FlightRequest;
import com.example.arilines.dto.FlightResponse;
import com.example.arilines.model.Airlines;
import com.example.arilines.repository.AirlinesRepository;
import com.speedment.jpastreamer.application.JPAStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlinesService {
    private final AirlinesRepository airlinesRepository;
    private final JPAStreamer jpaStreamer;

    public List<FlightResponse> getFlightsDetails(FlightRequest flightRequest) {
        List<Airlines> matchedAirlines = jpaStreamer.stream(Airlines.class)
                .filter(f -> f.getSource().equals(flightRequest.source()))
                .filter(f -> f.getDestination().equals(flightRequest.destination()))
                .filter(f -> f.getDepartureDate().toLocalDate().equals(flightRequest.date()))
                .toList();
        return airlinesToResponseDto(matchedAirlines);
    }

    private List<FlightResponse> airlinesToResponseDto(List<Airlines> matchedAirlines) {
        return matchedAirlines.stream().map(flight -> new FlightResponse(
                flight.getAirline(),
                flight.getSource(),
                flight.getDestination(),
                flight.getDuration(),
                flight.getFlightClass(),
                flight.getBookingSource(),
                flight.getBaseFare(),
                flight.getTax(),
                flight.getTotal(),
                flight.getSeasonality(),
                flight.getDaysBeforeDeparture()
        )).toList();
    }

}
