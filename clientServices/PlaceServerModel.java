package com.example.user.tapbar.clientServices;

public class PlaceServerModel {
    private double ltt;
    private double lon;
    private String name;
    private String address;
    private String city;
    private int avaliableTables;
    private int allTables;
    private String _id;

    public PlaceServerModel(double ltt, double lon, String name, String address, String city, int avaliableTables, int allTables) {
        this.ltt = ltt;
        this.lon = lon;
        this.name = name;
        this.address = address;
        this.city = city;
        this.avaliableTables = avaliableTables;
        this.allTables = allTables;
    }

    public double getLtt() {
        return ltt;
    }

    public double getLon() {
        return lon;
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

    public int getAvaliableTables() {
        return avaliableTables;
    }

    public int getAllTables() {
        return allTables;
    }

    public String getId() {
        return _id;
    }
}
