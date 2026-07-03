package com.jarvis.pnrchecker.model;

import java.util.ArrayList;
import java.util.List;

public class PnrResponse {

    private String pnr;
    private String trainNo;
    private String trainName;

    private String source;
    private String destination;

    private String journeyDate;
    private String bookingDate;

    private String boardingPoint;
    private String reservationUpto;

    private String quota;
    private String travelClass;

    private String chartPrepared;

    private int passengerCount;

    // New API Fields
    private int bookingFare;
    private int distance;

    private List<Passenger> passengers = new ArrayList<>();
    public PnrResponse() {

    }
    public String getPnr() {
        return pnr;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public String getReservationUpto() {
        return reservationUpto;
    }

    public String getQuota() {
        return quota;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public String getChartPrepared() {
        return chartPrepared;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getBookingFare() {
        return bookingFare;
    }

    public int getDistance() {
        return distance;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setJourneyDate(String journeyDate) {
        this.journeyDate = journeyDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public void setReservationUpto(String reservationUpto) {
        this.reservationUpto = reservationUpto;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public void setChartPrepared(String chartPrepared) {
        this.chartPrepared = chartPrepared;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public void setBookingFare(int bookingFare) {
        this.bookingFare = bookingFare;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    }