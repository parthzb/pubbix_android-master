package com.pubbix.feature.home.profile.datamodel;

import com.pubbix.data.model.User;
import com.wayfair.brickkit.brick.DataModel;

public class EditProfileDataModel extends DataModel {
    public String userId;
    public String registrationDate;
    public String profilePictureLastModified;
    public String fullName;
    public String email;
    public String phoneNumber;
    public String profilePicture;
    public String dayOfBirth;
    public String address;
    public String biography;
    public int gender;

    public EditProfileDataModel(User user) {
        this.userId = user.getUserId();
        this.registrationDate = user.getRegistrationDate();
        this.profilePictureLastModified = user.getProfilePictureLastModified();
        this.fullName = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.profilePicture = user.getProfileImage();
        this.dayOfBirth = user.getDayOfBirth();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.biography = user.getBiography();
    }
}
