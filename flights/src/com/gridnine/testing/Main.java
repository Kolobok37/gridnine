package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.models.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Перелеты до фильтрации:");
        System.out.println(flights + "\n");
        System.out.println("Исключены вылеты до текущего момента времени:");
        System.out.println(FlightFilter.filteringPastTime(flights) + "\n");
        System.out.println("Исключены перелёты, где имеются сегменты с датой прилёта раньше даты вылета:");
        System.out.println(FlightFilter.filteringDepartureBeforeArrival(flights) + "\n");
        System.out.println("Исключены перелёты, где пересадка превышает два час:");
        System.out.println(FlightFilter.filteringTransferLessTwoHours(flights));
    }
}