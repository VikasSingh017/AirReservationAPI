package com.vikas.AirReservation.dto;

import lombok.Data;

@Data
public class FlightSegmentDTO {
    private String carrier;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String classOfService; // ✅ Ensure this field exists
    private String cabinClass;
}
