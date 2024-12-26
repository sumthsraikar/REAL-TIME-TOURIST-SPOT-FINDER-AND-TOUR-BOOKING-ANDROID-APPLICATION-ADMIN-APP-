package com.example.yourapp; // Change this to your actual package name

public class TourBooking {
    private String userName;
    private String userPhone;
    private String tourName;
    private String tourDate;
    private int adults;
    private int childrenWithBed;
    private int childrenWithoutBed;
    private int infant;
    private double totalCost;

    // Constructor
    public TourBooking(String userName, String userPhone, String tourName, String tourDate,
                       int adults, int childrenWithBed, int childrenWithoutBed,
                       int infant, double totalCost) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.tourName = tourName;
        this.tourDate = tourDate;
        this.adults = adults;
        this.childrenWithBed = childrenWithBed;
        this.childrenWithoutBed = childrenWithoutBed;
        this.infant = infant;
        this.totalCost = totalCost;
    }

    // Getters and setters (if needed)
    // ...
}
