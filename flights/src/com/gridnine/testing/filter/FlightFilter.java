package com.gridnine.testing.filter;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {
    public static List<Flight> filteringPastTime(List<Flight> flights) {
        return flights.stream().filter(f -> f.getSegments()
                        .stream()
                        .allMatch(s -> s.getDepartureDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
    public static List<Flight> filteringDepartureBeforeArrival(List<Flight> flights) {
        return flights.stream().filter(f -> f.getSegments()
                        .stream()
                        .allMatch(s -> s.getDepartureDate().isBefore(s.getArrivalDate())))
                .collect(Collectors.toList());
    }

    public static List<Flight> filteringTransferLessTwoHours(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        boolean marker = true;
        F:
        for (Flight flight : flights) {
            Duration duration = Duration.ZERO;
            List<Segment> segments = flight.getSegments();
            for (int j = 0; j < segments.size() - 1; j++) {
                if (segments.get(j).getArrivalDate()
                        .isAfter(segments.get(j + 1).getDepartureDate())) {
                    continue F;
                }
                duration=duration.plus(Duration.between(segments.get(j).getArrivalDate(),segments.get(j + 1).getDepartureDate()));
            }
            if (duration.compareTo(Duration.ofHours(2))<1) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
