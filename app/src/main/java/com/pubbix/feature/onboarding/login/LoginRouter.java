package com.pubbix.feature.onboarding.login;

import android.content.Intent;

import com.pubbix.di.ScreenScope;
import com.pubbix.feature.home.MainActivity;
import com.pubbix.feature.onboarding.registration.RegistrationFragment;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class LoginRouter implements LoginContract.Router {
    private DefaultScreenNavigator mNavigator;
    @Inject
    public LoginRouter(DefaultScreenNavigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void goToHomepage(LoginFragment view) {
        view.startActivity(new Intent(view.getContext(), MainActivity.class));
    }

    @Override
    public void goToRegistration() {
        mNavigator.changeScreen(RegistrationFragment.newInstance());
    }
}
