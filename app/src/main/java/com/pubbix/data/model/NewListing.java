package com.pubbix.data.model;

/**
 * Created by AugusteC on 10/9/17.
 */

public class NewListing {
    private String userId;
    private String title;
    private String description;
    private String categoryId;
    private String price;
    private String currency;
    private String ownerUserName;
    private String ownerEmail;
    private String city;
    private String region;
    private String country;
    private String address;

    public NewListing(final String userId,
                      final String title,
                      final String description,
                      final String categoryId,
                      final String price,
                      final String currency,
                      final String ownerUserName,
                      final String ownerEmail,
                      final String city,
                      final String region,
                      final String country,
                      final String address) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.currency = currency;
        this.ownerUserName = ownerUserName;
        this.ownerEmail = ownerEmail;
        this.city = city;
        this.region = region;
        this.country = country;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(final String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(final String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
}
