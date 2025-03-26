package com.example.arilines.controller;

import com.example.arilines.dto.FlightRequest;
import com.example.arilines.dto.FlightResponse;
import com.example.arilines.service.AirlinesService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/airline")
@RequiredArgsConstructor
public class AirlinesController {
    private final AirlinesService airlinesService;

    @GetMapping
    public List<FlightResponse> getFlightsDetails(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        FlightRequest request = new FlightRequest(source, destination, date);
        return airlinesService.getFlightsDetails(request);
    }


}
