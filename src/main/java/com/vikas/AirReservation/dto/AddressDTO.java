package com.vikas.AirReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String addressName;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
