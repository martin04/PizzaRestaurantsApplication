package com.martin.pizzarestaurantsapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 8.2.16.
 */
public class Restaurant implements Parcelable {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private double distance;

    public Restaurant() {
    }

    public Restaurant(String name, double latitude, double longitude, double distance) {
        super();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public Restaurant(Parcel in){
        super();
        this.name = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.distance = in.readDouble();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeDouble(getLatitude());
        dest.writeDouble(getLongitude());
        dest.writeDouble(getDistance());
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}
