package com.pubbix.feature.common.datamodel;

import com.pubbix.data.model.Listing;
import com.wayfair.brickkit.brick.DataModel;

import java.util.List;

public class ListingDataModel extends DataModel {
    public String publishDate;
    public String categoryId;
    public String categoryName;
    public String listingId;
    public String title;
    public String description;
    public String status;
    public double fPrice;
    public Integer price;
    public String currency;
    public String address;
    public String city;
    public String country;
    public String region;
    public String publisherName;
    public String publisherEmail;
    public String publisherMobileNo;
    public String website;
    public List<String> images = null;

    public ListingDataModel(Listing listing) {
        this.publishDate = listing.publishDate;
        this.categoryId = listing.categoryId;
        this.categoryName = listing.categoryName;
        this.listingId = listing.listingId;
        this.title = listing.title;
        this.description = listing.description;
        this.status = listing.status;
        this.fPrice = listing.fPrice;
        this.price = listing.price;
        this.currency = listing.currency;
        this.address = listing.address;
        this.city = listing.city;
        this.country = listing.country;
        this.region = listing.region;
        this.publisherName = listing.publisherName;
        this.publisherEmail = listing.publisherEmail;
        this.publisherMobileNo = listing.publisherMobileNo;
        this.website = listing.website;
        this.images = listing.images;
    }
}
