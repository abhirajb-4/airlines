package com.example.arilines;

import com.example.arilines.model.Airlines;
import com.example.arilines.repository.AirlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FlightDataLoader implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(FlightDataLoader.class.getName());

    @Autowired
    private AirlinesRepository airlinesRepository;

    private static final String CSV_FILE_PATH = "flights.csv"; // Load from resources

    @Override
    public void run(String... args) {
        if (airlinesRepository.count() > 0) {
            LOGGER.info("Flights are already loaded. Skipping data import.");
            return;
        }

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CSV_FILE_PATH)) {
            if (inputStream == null) {
                LOGGER.severe("CSV file not found in resources!");
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withTrim()
                    .parse(reader);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            List<Airlines> flightsToSave = new ArrayList<>();

            for (CSVRecord record : records) {
                try {
                    Airlines flight = new Airlines();
                    flight.setAirline(record.get("Airline"));
                    flight.setSource(record.get("Source"));
                    flight.setSourceName(record.get("Source Name"));
                    flight.setDestination(record.get("Destination"));
                    flight.setDestinationName(record.get("Destination Name"));
                    flight.setDepartureDate(LocalDateTime.parse(record.get("Departure Date & Time"), formatter));
                    flight.setArrivalDate(LocalDateTime.parse(record.get("Arrival Date & Time"), formatter));
                    flight.setDuration(Double.parseDouble(record.get("Duration (hrs)")));
                    flight.setStopovers(record.get("Stopovers"));
                    flight.setAircraftType(record.get("Aircraft Type"));
                    flight.setFlightClass(record.get("Class"));
                    flight.setBookingSource(record.get("Booking Source"));
                    flight.setBaseFare(Double.parseDouble(record.get("Base Fare (BDT)")));
                    flight.setTax(Double.parseDouble(record.get("Tax & Surcharge (BDT)")));
                    flight.setTotal(Double.parseDouble(record.get("Total Fare (BDT)")));
                    flight.setSeasonality(record.get("Seasonality"));
                    flight.setDaysBeforeDeparture(Integer.parseInt(record.get("Days Before Departure")));

                    flightsToSave.add(flight);
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Error processing record: " + record.toString(), e);
                }
            }

            airlinesRepository.saveAll(flightsToSave);
            LOGGER.info("Successfully imported " + flightsToSave.size() + " flights.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load CSV file", e);
        }
    }
}