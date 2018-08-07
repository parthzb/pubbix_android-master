package com.pubbix.feature.home.profile.publicprofile.favorites;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class FavoritesListingRouter implements FavoritesListingContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public FavoritesListingRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
