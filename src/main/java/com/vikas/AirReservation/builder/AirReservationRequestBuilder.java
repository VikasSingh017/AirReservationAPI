package com.vikas.AirReservation.builder;


import com.vikas.AirReservation.dto.ReservationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AirReservationRequestBuilder {

    private static final String AGENCY_CODE = "TBO";
    private static final String BRANCH_CODE = "P7232098";

    public String buildRequest(ReservationRequestDto request) {
        StringBuilder soapRequest = new StringBuilder();

        // SOAP Header
        soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ")
                .append("xmlns:univ=\"http://www.travelport.com/schema/universal_v52_0\" ")
                .append("xmlns:air=\"http://www.travelport.com/schema/air_v52_0\" ")
                .append("xmlns:com=\"http://www.travelport.com/schema/common_v52_0\">")
                .append("<soapenv:Body>")
                .append("<univ:AirCreateReservationReq AuthorizedBy=\"").append(AGENCY_CODE).append("\" ")
                .append("TargetBranch=\"").append(BRANCH_CODE).append("\">")
                .append("<com:BillingPointOfSaleInfo OriginApplication=\"UAPI\"/>");

        // Travelers
        request.getTravelers().forEach(traveler -> {
            soapRequest.append("<com:BookingTraveler TravelerType=\"").append(traveler.getTravelerType()).append("\">")
                    .append("<com:BookingTravelerName First=\"").append(traveler.getFirstName()).append("\" ")
                    .append("Last=\"").append(traveler.getLastName()).append("\"/>")
                    .append("<com:PhoneNumber Number=\"").append(traveler.getPhoneNumber()).append("\" Type=\"Mobile\"/>")
                    .append("<com:Email EmailID=\"").append(traveler.getEmail()).append("\"/>")
                    .append("</com:BookingTraveler>");
        });

        // Flight Segments
        soapRequest.append("<univ:AirReservation><air:AirItinerary>");

        request.getFlightSegments().forEach(segment -> {
            soapRequest.append("<air:AirSegment Origin=\"").append(segment.getOrigin()).append("\" ")
                    .append("Destination=\"").append(segment.getDestination()).append("\" ")
                    .append("DepartureTime=\"").append(segment.getDepartureDate()).append("T00:00:00\" ")
                    .append("Carrier=\"").append(segment.getCarrier()).append("\" ")
                    .append("FlightNumber=\"").append(segment.getFlightNumber()).append("\" ")
                    .append("ClassOfService=\"").append(segment.getClassOfService()).append("\"/>");
        });

        // Pricing
        soapRequest.append("</air:AirItinerary>");

        request.getTravelers().forEach(traveler -> {
            soapRequest.append("<air:AirPricingInfo PricingMethod=\"Guaranteed\" FareBasisCode=\"")
                    .append(request.getFlightSegments().get(0).getFareBasisCode()).append("\">")
                    .append("<air:FareInfo FareBasis=\"").append(request.getFlightSegments().get(0).getFareBasisCode()).append("\"/>")
                    .append("<air:PassengerType Code=\"").append(traveler.getTravelerType()).append("\"/>")
                    .append("</air:AirPricingInfo>");
        });

        // Close tags
        soapRequest.append("</univ:AirReservation>")
                .append("</univ:AirCreateReservationReq>")
                .append("</soapenv:Body>")
                .append("</soapenv:Envelope>");

        return soapRequest.toString();
    }
}