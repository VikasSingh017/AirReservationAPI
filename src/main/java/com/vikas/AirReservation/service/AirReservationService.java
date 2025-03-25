package com.vikas.AirReservation.service;


import com.vikas.AirReservation.builder.AirReservationRequestBuilder;
import com.vikas.AirReservation.dto.ReservationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AirReservationService {

    private final WebClient webClient;
    private final AirReservationRequestBuilder requestBuilder;

    @Autowired
    public AirReservationService(WebClient webClient, AirReservationRequestBuilder requestBuilder) {
        this.webClient = webClient;
        this.requestBuilder = requestBuilder;
    }

    public Mono<String> createReservation(ReservationRequestDto request) {
        String soapRequest = requestBuilder.buildRequest(request);

        return webClient.post()
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.APPLICATION_XML)
                .bodyValue(soapRequest)
                .retrieve()
                .bodyToMono(String.class);
    }
}