package com.pubbix.data.model;

/**
 * Created by AugusteC on 6/15/17.
 */

public class Credentials {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String facebookId;

    public Credentials(final String fullName,
                       final String email,
                       final String phoneNumber,
                       final String profilePicture,
                       final String facebookId) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.facebookId = facebookId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(final String facebookId) {
        this.facebookId = facebookId;
    }
}
