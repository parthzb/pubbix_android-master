package com.pubbix.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {
    private String userId;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("address")
    @Expose
    public String address;

    public Address(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public Address(String country, String state, String city, String address) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    //TODO find a better solution
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
