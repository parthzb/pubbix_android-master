package com.pubbix.feature.home.profile.edit.generic;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class GenericProfileEditRouter implements GenericProfileEditContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public GenericProfileEditRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void goBack() {
        navigator.pop();
    }
}
