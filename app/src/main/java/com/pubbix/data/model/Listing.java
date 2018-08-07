package com.pubbix.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listing {

    @SerializedName("publish_date")
    @Expose
    public String publishDate;
    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("category_name")
    @Expose
    public String categoryName;
    @SerializedName("listing_id")
    @Expose
    public String listingId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("f_price")
    @Expose
    public double fPrice;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("publisher_name")
    @Expose
    public String publisherName;
    @SerializedName("publisher_email")
    @Expose
    public String publisherEmail;
    @SerializedName("publisher_mobile_no")
    @Expose
    public String publisherMobileNo;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("images")
    @Expose
    public List<String> images = null;

}
