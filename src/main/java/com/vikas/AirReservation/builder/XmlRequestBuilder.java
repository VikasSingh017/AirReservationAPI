//package com.vikas.AirReservation.builder;
//
//import com.vikas.AirReservation.dto.*;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class XmlRequestBuilder {
//
//    public String buildAirCreateReservationRequest(UniversalRecordDTO recordDTO) {
//        StringBuilder soapRequest = new StringBuilder();
//
//        // SOAP Envelope
//        soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">")
//                .append("<soapenv:Header/>")
//                .append("<soapenv:Body>")
//                .append("<univ:AirCreateReservationReq ")
//                .append("xmlns:univ=\"http://www.travelport.com/schema/universal_v52_0\" ")
//                .append("xmlns:com=\"http://www.travelport.com/schema/common_v52_0\" ")
//                .append("xmlns:air=\"http://www.travelport.com/schema/air_v52_0\" ")
//                .append("AuthorizedBy=\"TBO\" TargetBranch=\"P7232098\" ")
//                .append("TraceId=\"PP_1G_").append(UUID.randomUUID().toString().substring(0, 8)).append("\">");
//
//        // Billing Info
//        soapRequest.append("<com:BillingPointOfSaleInfo OriginApplication=\"UAPI\"/>");
//
//        // Travelers
//        if (recordDTO.getTravelers() != null) {
//            for (BookingTravelerDTO traveler : recordDTO.getTravelers()) {
//                soapRequest.append(buildTravelerSegment(traveler));
//            }
//        }
//
//        // Continuity Check
//        soapRequest.append("<com:ContinuityCheckOverride>true</com:ContinuityCheckOverride>");
//
//        // Flight Segments
//        if (recordDTO.getFlightSegments() != null) {
//            soapRequest.append("<air:AirPricingSolution>");
//            for (FlightSegmentDTO segment : recordDTO.getFlightSegments()) {
//                soapRequest.append(buildFlightSegment(segment));
//            }
//            soapRequest.append("</air:AirPricingSolution>");
//        }
//
//        // Form of Payment
//        soapRequest.append("<com:FormOfPayment Type=\"Cash\"/>");
//
//        // Action Status
//        soapRequest.append("<com:ActionStatus Type=\"ACTIVE\" ProviderCode=\"1G\"/>");
//
//        // Close tags
//        soapRequest.append("</univ:AirCreateReservationReq>")
//                .append("</soapenv:Body>")
//                .append("</soapenv:Envelope>");
//
//        return soapRequest.toString();
//    }
//
//    private String buildTravelerSegment(BookingTravelerDTO traveler) {
//        return "<com:BookingTraveler TravelerType=\"" + traveler.getTravelerType() + "\" Gender=\"" + traveler.getGender() + "\">" +
//                "<com:BookingTravelerName First=\"" + traveler.getFirstName() + "\" Last=\"" + traveler.getLastName() + "\" Prefix=\"" + traveler.getPrefix() + "\"/>" +
//                "<com:PhoneNumber Number=\"" + traveler.getPhoneNumber() + "\" Type=\"Mobile\"/>" +
//                "<com:Email EmailID=\"" + traveler.getEmail() + "\"/>" +
//                buildAddressSegment(traveler.getAddress()) +
//                "</com:BookingTraveler>";
//    }
//
//    private String buildFlightSegment(FlightSegmentDTO segment) {
//        return "<air:AirSegment " +
//                "Carrier=\"" + segment.getCarrier() + "\" " +
//                "FlightNumber=\"" + segment.getFlightNumber() + "\" " +
//                "Origin=\"" + segment.getOrigin() + "\" " +
//                "Destination=\"" + segment.getDestination() + "\" " +
//                "DepartureTime=\"" + segment.getDepartureTime() + "\" " +
//                "ArrivalTime=\"" + segment.getArrivalTime() + "\" " +
//                "ClassOfService=\"" + segment.getClassOfService() + "\" " +
//                "ProviderCode=\"1G\">" +
//                "<air:FlightDetails Origin=\"" + segment.getOrigin() + "\" Destination=\"" + segment.getDestination() + "\"/>" +
//                "</air:AirSegment>";
//    }
//
//    private String buildAddressSegment(AddressDTO address) {
//        if (address == null) return "";
//
//        return "<com:Address>" +
//                "<com:Street>" + address.getStreet() + "</com:Street>" +
//                "<com:City>" + address.getCity() + "</com:City>" +
//                "<com:State>" + address.getState() + "</com:State>" +
//                "<com:PostalCode>" + address.getPostalCode() + "</com:PostalCode>" +
//                "<com:Country>" + address.getCountry() + "</com:Country>" +
//                "</com:Address>";
//    }
//}