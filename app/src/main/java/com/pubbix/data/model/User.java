package com.pubbix.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("registrationDate")
    @Expose
    private String registrationDate;

    @SerializedName("profilePictureLastModified")
    @Expose
    private String profilePictureLastModified = "";

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("profilePicture")
    @Expose
    private String profilePicture;

    @SerializedName("dayOfBirth")
    @Expose
    private String dayOfBirth;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("gender")
    @Expose
    private int gender;

    @SerializedName("biography")
    @Expose
    private String biography;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("city")
    @Expose
    private String city;


    public User() {
    }

    public User(final String userId,
                final String name,
                final String email,
                final String phoneNumber,
                final String profileImage) {
        this.userId = userId;
        this.fullName = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profileImage;
        this.dayOfBirth = null;
        this.address = null;
        this.gender = 0;
        this.biography = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profilePicture;
    }

    public void setProfileImage(String profileImage) {
        this.profilePicture = profileImage;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(final String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(final int gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfilePictureLastModified() {
        return profilePictureLastModified;
    }

    public void setProfilePictureLastModified(String profilePictureLastModified) {
        this.profilePictureLastModified = profilePictureLastModified;
    }
}
