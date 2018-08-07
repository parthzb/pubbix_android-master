package com.pubbix.feature.onboarding.registration.viewmodel;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.pubbix.feature.onboarding.registration.RegistrationContract;

public class RegistrationViewModel extends BaseObservable {
    private RegistrationContract.Presenter presenter;
    private Resources resources;
    private String fullName;
    private String email;

    public RegistrationViewModel(RegistrationContract.Presenter presenter, Resources resources) {
        this.presenter = presenter;
        this.resources = resources;
    }

    @Bindable
    public String getFullName() {
        if (fullName == null) {
            return "";
        }
        return fullName;
    }

    @Bindable
    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    @Bindable
    public View.OnClickListener getOnBackButtonClicked() {
        return view -> presenter.onBackButtonClicked();
    }

    @Bindable
    public View.OnClickListener getOnProfilePictureSelected() {
        return view -> presenter.onProfilePictureSelected();
    }

    @Bindable
    public View.OnClickListener getNextPageButtonClicked() {
        return view -> presenter.onNextButtonClicked();
    }
}
