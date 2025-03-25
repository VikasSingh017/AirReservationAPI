package com.vikas.AirReservation.dto;

import lombok.Data;

@Data
public class FlightSegmentDto {
    private String origin;
    private String destination;
    private String departureDate; // YYYY-MM-DD
    private String carrier;
    private String flightNumber;
    private String classOfService;
    private String fareBasisCode;
}