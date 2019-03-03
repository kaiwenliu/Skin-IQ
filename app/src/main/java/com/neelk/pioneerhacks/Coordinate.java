package com.neelk.pioneerhacks;

public class Coordinate {
    private double lat;
    private double lng;
    private String title;
    public Coordinate(double lat, double lng, String title){
        this.lat = lat;
        this.lng = lng;
        this.title= title;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getTitle() {
        return title;
    }
}
