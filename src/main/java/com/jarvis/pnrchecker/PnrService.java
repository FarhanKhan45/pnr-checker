package com.jarvis.pnrchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarvis.pnrchecker.model.Passenger;
import com.jarvis.pnrchecker.model.PnrResponse;

@Service
public class PnrService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${rapidapi.host}")
    private String apiHost;

    public PnrResponse checkPnr(String pnr) throws Exception {

    	String url = "https://irctc-indian-railway-pnr-status.p.rapidapi.com/getPNRStatus/" + pnr;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", apiKey);
        headers.set("x-rapidapi-host", "irctc-indian-railway-pnr-status.p.rapidapi.com");
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        JsonNode data = root.get("data");

        if (data == null || data.isNull()) {
            throw new RuntimeException("Unable to fetch PNR details. Please check the PNR number and try again.");
        }
        

        PnrResponse pnrResponse = new PnrResponse();
        
     // Train Information
//        pnrResponse.setPnr(data.get("Pnr").asText());
//        pnrResponse.setTrainNo(data.get("TrainNo").asText());
//        pnrResponse.setTrainName(data.get("TrainName").asText());
//
//        pnrResponse.setJourneyDate(data.get("Doj").asText());
//        pnrResponse.setBookingDate(data.get("BookingDate").asText());
//
//        pnrResponse.setQuota(data.get("Quota").asText());
//
//        pnrResponse.setSource(data.get("SourceName").asText());
//        pnrResponse.setDestination(data.get("DestinationName").asText());
//
//        pnrResponse.setBoardingPoint(data.get("BoardingStationName").asText());
//        pnrResponse.setReservationUpto(data.get("ReservationUptoName").asText());
//
//        pnrResponse.setTravelClass(data.get("Class").asText());
//
//        pnrResponse.setChartPrepared(
//                data.get("ChartPrepared").asBoolean() ? "Yes" : "No");
//
//        pnrResponse.setPassengerCount(data.get("PassengerCount").asInt());

//        // Ratings
//        pnrResponse.setTrainRating(data.get("Rating").asText());
//        pnrResponse.setFoodRating(data.get("FoodRating").asText());
//        pnrResponse.setPunctualityRating(data.get("PunctualityRating").asText());
//        pnrResponse.setCleanlinessRating(data.get("CleanlinessRating").asText());
//        
        
     // Train Information
        pnrResponse.setPnr(data.get("pnrNumber").asText());
        pnrResponse.setTrainNo(data.get("trainNumber").asText());
        pnrResponse.setTrainName(data.get("trainName").asText());

        pnrResponse.setJourneyDate(data.get("dateOfJourney").asText());
        pnrResponse.setBookingDate(data.get("bookingDate").asText());

        pnrResponse.setQuota(data.get("quota").asText());

        pnrResponse.setSource(data.get("sourceStation").asText());
        pnrResponse.setDestination(data.get("destinationStation").asText());

        pnrResponse.setBoardingPoint(data.get("boardingPoint").asText());
        pnrResponse.setReservationUpto(data.get("reservationUpto").asText());

        pnrResponse.setTravelClass(data.get("journeyClass").asText());

        String chart = data.get("chartStatus").asText();

        if (chart.equalsIgnoreCase("Chart Prepared")) {
            pnrResponse.setChartPrepared("Yes");
        } else {
            pnrResponse.setChartPrepared("No");
        }

        pnrResponse.setPassengerCount(data.get("numberOfpassenger").asInt());
        pnrResponse.setBookingFare(data.get("bookingFare").asInt());
        pnrResponse.setDistance(data.get("distance").asInt());
     // Passenger Details
//        JsonNode passengers = data.get("PassengerStatus");
//
//        for (JsonNode p : passengers) {
//
//            Passenger passenger = new Passenger();
//
//            passenger.setPassengerNo(p.get("Number").asInt());
//
//            passenger.setBookingStatus(
//                    p.get("BookingStatus").asText());
//
//            passenger.setCurrentStatus(
//                    p.get("CurrentStatus").asText());
//
//            passenger.setPrediction(
//                    p.get("Prediction").asText());
//
//            if (p.get("PredictionPercentage").isNull()) {
//                passenger.setPredictionPercentage("N/A");
//            } else {
//                passenger.setPredictionPercentage(
//                        p.get("PredictionPercentage").asText());
//            }
//
//            passenger.setCoach(
//                    p.get("Coach").asText());
//
//            int berthNo = p.get("CurrentBerthNo").asInt();
//
//            String berth = berthNo + " (" +
//                    getBerthType(berthNo, pnrResponse.getTravelClass()) + ")";
//
//            passenger.setBerth(berth);
//            pnrResponse.getPassengers().add(passenger);
//        }
     // Passenger Details

        JsonNode passengers = data.get("passengerList");

        for (JsonNode p : passengers) {

            Passenger passenger = new Passenger();

            passenger.setPassengerNo(
                    p.get("passengerSerialNumber").asInt());

            passenger.setBookingStatus(
                    p.get("bookingStatusDetails").asText());

            passenger.setCurrentStatus(
                    p.get("currentStatusDetails").asText());

            // This API doesn't provide prediction
            passenger.setPrediction("N/A");
            passenger.setPredictionPercentage("N/A");

            passenger.setCoach(
                    p.get("currentCoachId").asText());

            int berthNo = p.get("currentBerthNo").asInt();

            if (berthNo == 0) {
                passenger.setBerth("Not Allotted");
            } else {

                String berth = berthNo + " (" +
                        getBerthType(
                                berthNo,
                                pnrResponse.getTravelClass())
                        + ")";

                passenger.setBerth(berth);
            }

            // IMPORTANT
            pnrResponse.getPassengers().add(passenger);
        }
        return pnrResponse;
    }

    private String getBerthType(int berthNo, String travelClass) {

        // Sleeper & 3A
        if ("SL".equalsIgnoreCase(travelClass) ||
            "3A".equalsIgnoreCase(travelClass)) {

            switch (berthNo % 8) {

                case 1:
                case 4:
                    return "Lower Berth";

                case 2:
                case 5:
                    return "Middle Berth";

                case 3:
                case 6:
                    return "Upper Berth";

                case 7:
                    return "Side Lower";

                case 0:
                    return "Side Upper";
            }
        }

        // 2A
        else if ("2A".equalsIgnoreCase(travelClass)) {

            switch (berthNo % 6) {

                case 1:
                case 3:
                    return "Lower Berth";

                case 2:
                case 4:
                    return "Upper Berth";

                case 5:
                    return "Side Lower";

                case 0:
                    return "Side Upper";
            }
        }

        // 3E
        else if ("3E".equalsIgnoreCase(travelClass)) {

            switch (berthNo % 8) {

                case 1:
                case 4:
                    return "Lower Berth";

                case 2:
                case 5:
                    return "Middle Berth";

                case 3:
                case 6:
                    return "Upper Berth";

                case 7:
                    return "Side Lower";

                case 0:
                    return "Side Upper";
            }
        }

        return "Unknown";
    }
}
        