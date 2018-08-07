package com.pubbix.feature.onboarding.registration;

import android.content.Intent;

import com.pubbix.di.ScreenScope;
import com.pubbix.feature.home.MainActivity;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class RegistrationRouter implements RegistrationContract.Router {
    private DefaultScreenNavigator navigator;

    @Inject
    RegistrationRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void goBack() {
        navigator.pop();
    }

    @Override
    public void goToHomepage(RegistrationFragment view) {
        view.startActivity(new Intent(view.getContext(), MainActivity.class));
    }
}
