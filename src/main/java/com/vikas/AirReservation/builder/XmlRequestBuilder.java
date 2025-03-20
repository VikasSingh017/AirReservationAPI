package com.vikas.AirReservation.builder;

import com.vikas.AirReservation.dto.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XmlRequestBuilder {

    public String buildSoapResponse(UniversalRecordDTO recordDTO) {
        StringBuilder soapResponseXml = new StringBuilder();

        // ✅ Generate dynamic TraceId and TransactionId
        String traceId = "PP_1G_" + UUID.randomUUID().toString().substring(0, 8);
        String transactionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String responseTime = "2000";  // Can be dynamically calculated if needed

        // ✅ SOAP Envelope Start
        soapResponseXml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ")
                .append("xmlns:universal=\"http://www.travelport.com/schema/universal_v52_0\" ")
                .append("xmlns:common_v52_0=\"http://www.travelport.com/schema/common_v52_0\" ")
                .append("xmlns:air=\"http://www.travelport.com/schema/air_v52_0\">")
                .append("<soapenv:Body>")
                .append("<universal:AirCreateReservationRsp TraceId=\"").append(traceId).append("\" ")
                .append("TransactionId=\"").append(transactionId).append("\" ResponseTime=\"").append(responseTime).append("\">")
                .append("<universal:UniversalRecord LocatorCode=\"").append(recordDTO.getLocatorCode()).append("\" Version=\"1\" Status=\"Active\">");

        // ✅ Add Traveler Details
        if (recordDTO.getTravelers() != null && !recordDTO.getTravelers().isEmpty()) {
            for (BookingTravelerDTO traveler : recordDTO.getTravelers()) {
                soapResponseXml.append(buildTravelerSegment(traveler));
            }
        }

        // ✅ Add Flight Segments
        if (recordDTO.getFlightSegments() != null && !recordDTO.getFlightSegments().isEmpty()) {
            for (FlightSegmentDTO segment : recordDTO.getFlightSegments()) {
                soapResponseXml.append(buildFlightSegment(segment));
            }
        }

        // ✅ Add Pricing Details
        if (recordDTO.getPricing() != null) {
            soapResponseXml.append(buildPricingInfo(recordDTO.getPricing()));
        }

        soapResponseXml.append("</universal:UniversalRecord>")
                .append("</universal:AirCreateReservationRsp>")
                .append("</soapenv:Body>")
                .append("</soapenv:Envelope>");

        return soapResponseXml.toString();
    }

    private String buildTravelerSegment(BookingTravelerDTO traveler) {
        return "<common_v52_0:BookingTraveler TravelerType=\"ADT\" Gender=\"M\">"
                + "<common_v52_0:BookingTravelerName Prefix=\"MR\">"
                + "<common_v52_0:First>" + traveler.getFirstName() + "</common_v52_0:First>"
                + "<common_v52_0:Last>" + traveler.getLastName() + "</common_v52_0:Last>"
                + "</common_v52_0:BookingTravelerName>"
                + "<common_v52_0:PhoneNumber Type=\"Mobile\" Location=\"DEL\" Number=\"" + traveler.getPhoneNumber() + "\" />"
                + "<common_v52_0:Email EmailID=\"" + traveler.getEmail() + "\" />"
                + buildAddressSegment(traveler.getAddress())  // ✅ Pass dynamic address
                + "</common_v52_0:BookingTraveler>";
    }


    private String buildFlightSegment(FlightSegmentDTO segment) {
        return "<air:AirSegment Carrier=\"" + segment.getCarrier() + "\" FlightNumber=\"" + segment.getFlightNumber() + "\" "
                + "Origin=\"" + segment.getOrigin() + "\" Destination=\"" + segment.getDestination() + "\" "
                + "DepartureTime=\"" + segment.getDepartureTime() + "\" ArrivalTime=\"" + segment.getArrivalTime() + "\" "
                + "ClassOfService=\"" + segment.getClassOfService() + "\" CabinClass=\"" + segment.getCabinClass() + "\" "
                + "Status=\"HK\" ProviderCode=\"1G\" TravelTime=\"230\" />";
    }

    private String buildPricingInfo(AirPricingDTO pricing) {
        return "<air:AirPricingInfo TotalPrice=\"" + pricing.getTotalPrice() + "\" BasePrice=\"" + pricing.getBasePrice() + "\" "
                + "Taxes=\"" + pricing.getTaxes() + "\" ApproximateTotalPrice=\"" + pricing.getTotalPrice() + "\" "
                + "Refundable=\"true\" ETicketability=\"Yes\" />";
    }

    private String buildAddressSegment(AddressDTO address) {
        if (address == null) {
            return "";  // ✅ If no address is provided, return an empty string
        }

        return "<common_v52_0:Address>"
                + "<common_v52_0:AddressName>" + address.getAddressName() + "</common_v52_0:AddressName>"
                + "<common_v52_0:Street>" + address.getStreet() + "</common_v52_0:Street>"
                + "<common_v52_0:City>" + address.getCity() + "</common_v52_0:City>"
                + "<common_v52_0:State>" + address.getState() + "</common_v52_0:State>"
                + "<common_v52_0:PostalCode>" + address.getPostalCode() + "</common_v52_0:PostalCode>"
                + "<common_v52_0:Country>" + address.getCountry() + "</common_v52_0:Country>"
                + "</common_v52_0:Address>";
    }

}
