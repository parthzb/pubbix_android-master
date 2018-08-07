package com.pubbix.feature.home.profile.datamodel;

import com.pubbix.data.model.User;
import com.wayfair.brickkit.brick.DataModel;

public class UserFullNameProfilePictureDataModel extends DataModel{
    private String userFullName;
    private String profilePicture;
    private String profilePictureLastModified;

    public UserFullNameProfilePictureDataModel() {
    }

    public UserFullNameProfilePictureDataModel(User user) {
        this.userFullName = user.getName();
        this.profilePicture = user.getProfileImage();
        this.profilePictureLastModified = user.getProfilePictureLastModified();
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePictureLastModified() {
        return profilePictureLastModified;
    }

    public void setProfilePictureLastModified(String profilePictureLastModified) {
        this.profilePictureLastModified = profilePictureLastModified;
    }
}
