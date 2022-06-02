package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GolfCourse {

    private Long id;
    @JsonProperty("courseName")
    private String name;
    private String address;
    private String city;
    private String state;
    private int zip;
    private double longitude;
    private double latitude;

    public GolfCourse() { }

    public GolfCourse(Long id, String name, String address, String city, String state, int zip, double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
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

    public String getState() {
        return state;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Course Name: " + name + " Address: " + address + " " + city + ", " + state + " " + zip + latitude + longitude;
    }
}
