package com.pubbix.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listings {

    @SerializedName("statusCode")
    @Expose
    public int statusCode;
    @SerializedName("listings")
    @Expose
    List<Listing> mListings = null;

    public List<Listing> getListings() {
        return mListings;
    }

    public void setListings(List<Listing> listings) {
        this.mListings = listings;
    }

}
