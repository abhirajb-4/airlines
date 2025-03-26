package com.example.arilines.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Airlines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String source;
    private String sourceName;
    private String destination;
    private String destinationName;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private double duration;
    private String stopovers;
    private String aircraftType;
    private String flightClass;
    private String bookingSource;
    private double baseFare;
    private double tax;
    private double total;
    private String seasonality;
    private int daysBeforeDeparture;
}
