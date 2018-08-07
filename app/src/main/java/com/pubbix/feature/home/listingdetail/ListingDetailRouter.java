package com.pubbix.feature.home.listingdetail;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class ListingDetailRouter implements ListingDetailContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public ListingDetailRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
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
