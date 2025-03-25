package com.vikas.AirReservation.contoller;


import com.vikas.AirReservation.dto.ReservationRequestDto;
import com.vikas.AirReservation.service.AirReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/air")
public class AirReservationController {

    private final AirReservationService reservationService;

    @Autowired
    public AirReservationController(AirReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/reservations", produces = MediaType.APPLICATION_XML_VALUE)
    public Mono<ResponseEntity<String>> createReservation(@RequestBody ReservationRequestDto request) {
        return reservationService.createReservation(request)
                .map(response -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_XML)
                        .body(response))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.internalServerError()
                                .body("Error creating reservation: " + e.getMessage())));
    }
}