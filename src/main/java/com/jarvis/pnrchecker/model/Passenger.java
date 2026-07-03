package com.jarvis.pnrchecker.model;

public class Passenger {

    private int passengerNo;
    private String bookingStatus;
    private String currentStatus;
    private String coach;
    private String berth;
    private String prediction;
    private String predictionPercentage;

    public Passenger() {
    }

    public int getPassengerNo() {
        return passengerNo;
    }

    public void setPassengerNo(int passengerNo) {
        this.passengerNo = passengerNo;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getBerth() {
        return berth;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public String getPredictionPercentage() {
        return predictionPercentage;
    }

    public void setPredictionPercentage(String predictionPercentage) {
        this.predictionPercentage = predictionPercentage;
    }
}