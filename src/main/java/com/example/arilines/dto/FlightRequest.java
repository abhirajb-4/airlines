package com.example.arilines.dto;

import java.time.LocalDate;

public record FlightRequest (String source,
        String destination,
        LocalDate date){
}
