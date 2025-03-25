//package com.vikas.AirReservation.contoller;
//
//import com.vikas.AirReservation.builder.XmlRequestBuilder;
//import com.vikas.AirReservation.dto.UniversalRecordDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/reservation")
//public class ReservationController {
//
//    @Autowired
//    private XmlRequestBuilder xmlRequestBuilder;
//
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
//    public ResponseEntity<Mono<String>> createReservation(@RequestBody UniversalRecordDTO request) {
//        try {
//            String soapRequest = xmlRequestBuilder.buildAirCreateReservationRequest(request);
//
//            Mono<String> response = webClientBuilder.build()
//                    .post()
//                    .uri("/AirService")
//                    .bodyValue(soapRequest)
//                    .retrieve()
//                    .bodyToMono(String.class);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(500)
//                    .body(Mono.just("<error>Failed to process reservation request: " + e.getMessage() + "</error>"));
//        }
//    }
//}