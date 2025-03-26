package com.example.arilines.dto;

public record FlightResponse(
        String airlineName,
        String sourceName,
        String destination,
        double duration,
        String flightClass,
        String bookingSource,
        double baseFare,
        double tax,
        double total,
        String seasonality,
        int daysBeforeDeparture
) {
}
