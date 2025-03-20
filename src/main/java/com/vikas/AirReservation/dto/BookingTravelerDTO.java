package com.vikas.AirReservation.dto;



import lombok.Data;

@Data
public class BookingTravelerDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AddressDTO address;
}

