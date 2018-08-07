package com.pubbix.feature.home.profile.publicprofile;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.feature.home.profile.edit.EditProfileFragment;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class   PublicProfileRouter implements PublicProfileContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public PublicProfileRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }


    @Override
    public void goToEditPage() {
        navigator.changeScreen(EditProfileFragment.newInstance());
    }

    @Override
    public void goBack() {
        navigator.pop();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
