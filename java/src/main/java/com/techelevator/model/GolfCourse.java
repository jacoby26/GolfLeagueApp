package com.techelevator.model;

public class GolfCourse {

    private String name;
    private String address;
    private String city;
    private int zip;
    private double longitude;
    private double latitude;

    public GolfCourse(String name, String address, String city, int zip, double longitude, double latitude) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
