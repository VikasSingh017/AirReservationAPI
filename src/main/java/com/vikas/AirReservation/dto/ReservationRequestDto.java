package com.vikas.AirReservation.dto;

import com.vikas.AirReservation.dto.BookingTravelerDto;
import com.vikas.AirReservation.dto.FlightSegmentDto;
import lombok.Data;
import java.util.List;

@Data
public class ReservationRequestDto {
    private List<BookingTravelerDto> travelers;
    private List<FlightSegmentDto> flightSegments;
}