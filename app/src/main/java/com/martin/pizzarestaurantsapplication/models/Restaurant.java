package com.martin.pizzarestaurantsapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 8.2.16.
 */
public class Restaurant implements Parcelable {

    private Long id;
    private String name;
    private float latitude;
    private float longitude;
    private float distance;

    public Restaurant() {
    }

    public Restaurant(String name, float latitude, float longitude, float distance) {
        super();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public Restaurant(Parcel in){
        super();
        this.name = in.readString();
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
        this.distance = in.readFloat();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeFloat(getLatitude());
        dest.writeFloat(getLongitude());
        dest.writeFloat(getDistance());
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
