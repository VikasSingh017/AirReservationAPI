package com.vikas.AirReservation.contoller;

import com.vikas.AirReservation.builder.XmlRequestBuilder;
import com.vikas.AirReservation.dto.UniversalRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private XmlRequestBuilder xmlRequestBuilder;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> createReservation(@RequestBody UniversalRecordDTO request) {
        try {
            // Generate SOAP response
            String xmlResponse = xmlRequestBuilder.buildSoapResponse(request);
            return ResponseEntity.ok(xmlResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("<error>Failed to process reservation request</error>");
        }
    }
}
