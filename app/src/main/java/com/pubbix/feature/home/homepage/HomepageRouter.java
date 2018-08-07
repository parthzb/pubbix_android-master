package com.pubbix.feature.home.homepage;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.home.listingdetail.ListingDetailFragment;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class HomepageRouter implements HomepageContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public HomepageRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void goBack() {
        navigator.pop();
    }

    @Override
    public void goToListingDetailPage(ListingDataModel listingDataModel) {
        ListingDetailFragment fragment = ListingDetailFragment.newInstance();
        fragment.setListing(listingDataModel);
        navigator.changeScreen(fragment);
    }
}
