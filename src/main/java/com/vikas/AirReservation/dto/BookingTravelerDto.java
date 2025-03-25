package com.vikas.AirReservation.dto;

import lombok.Data;

@Data
public class BookingTravelerDto {
    private String firstName;
    private String lastName;
    private String travelerType; // ADT, CNN, INF
    private String phoneNumber;
    private String email;
}