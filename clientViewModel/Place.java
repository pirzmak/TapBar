package com.example.user.tapbar.clientViewModel;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.user.tapbar.clientServices.PlaceServerModel;
import com.google.android.gms.maps.model.LatLng;


public class Place implements Parcelable{
    private LatLng position;
    private String name;
    private String address;
    private String city;
    private int avaliableTables;
    private int allTables;
    private int color;
    private String _id;


    public Place(double ltt, double lon, String nn, String add){
        this.position = new LatLng(ltt,lon);
        this.name = nn;
        this.address = add;
        this.city = "Warszawa";
        this.color = Color.RED;
    }

    public Place(LatLng position, String name, String address, String city, int avaliableTables, int allTables) {
        this.position = position;
        this.name = name;
        this.address = address;
        this.city = city;
        this.avaliableTables = avaliableTables;
        this.allTables = allTables;
    }

    public Place(double ltt, double lon, String nn, String add, String mm, int cc){
        this.position = new LatLng(ltt,lon);
        this.name = nn;
        this.address = add;
        this.city = mm;
        this.color = cc;
    }

    public Place(PlaceServerModel p){
        this.position = new LatLng(p.getLtt(),p.getLon());;
        this.name = p.getName();
        this.address = p.getAddress();
        this.city = p.getCity();
        this.avaliableTables = p.getAvaliableTables();
        this.allTables = p.getAllTables();
        this._id = p.getId();
    }

    protected Place(Parcel in) {
        position = in.readParcelable(LatLng.class.getClassLoader());
        name = in.readString();
        address = in.readString();
        city = in.readString();
        color = in.readInt();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public LatLng getPos() {
        return position;
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

    public String getId() {
        return _id;
    }

    public int getAllTables() {
        return allTables;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(position, i);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeInt(color);
    }
}
