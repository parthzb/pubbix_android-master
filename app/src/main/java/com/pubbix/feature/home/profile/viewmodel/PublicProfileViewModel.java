package com.pubbix.feature.home.profile.viewmodel;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.pubbix.feature.home.profile.publicprofile.PublicProfileContract;
import com.pubbix.util.UserHelper;

public class PublicProfileViewModel extends BaseObservable {
    private PublicProfileContract.Presenter presenter;
    private Resources resources;
    private UserHelper userHelper;

    public PublicProfileViewModel(PublicProfileContract.Presenter presenter,
                                  Resources resources, UserHelper userHelper) {
        this.presenter = presenter;
        this.resources = resources;
        this.userHelper = userHelper;
    }

    @Bindable
    public String getFullName() {
        return userHelper.getUser().getName();
    }

    @Bindable
    public String getCountry() {
        return userHelper.getUser().getAddress();
    }

    @Bindable
    public String getProfilePicture() {
        return userHelper.getUser().getProfileImage();
    }

    @Bindable
    public String getInvalidationKey() {
        return userHelper.getUser().getProfilePictureLastModified();
    }

    @Bindable
    public View.OnClickListener getOnEditProfileButtonClicked() {
        return view -> presenter.onEditButtonClicked();
    }

    @Bindable
    public View.OnClickListener getOnBackButtonClicked() {
        return view -> presenter.onBackButtonClicked();
    }
}
