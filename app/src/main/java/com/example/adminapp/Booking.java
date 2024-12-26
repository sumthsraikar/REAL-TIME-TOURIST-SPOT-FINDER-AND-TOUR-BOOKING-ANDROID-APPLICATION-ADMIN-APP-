package com.example.adminapp;
public class Booking {
    private String userName;
    private String userPhone;
    private String tourName;
    private String tourDate;
    private double totalCost;

    public Booking(String userName, String userPhone, String tourName, String tourDate, double totalCost) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.tourName = tourName;
        this.tourDate = tourDate;
        this.totalCost = totalCost;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getTourName() {
        return tourName;
    }

    public String getTourDate() {
        return tourDate;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
