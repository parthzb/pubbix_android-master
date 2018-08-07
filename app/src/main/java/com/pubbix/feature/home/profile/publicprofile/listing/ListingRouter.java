package com.pubbix.feature.home.profile.publicprofile.listing;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.home.listingdetail.ListingDetailFragment;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class ListingRouter implements ListingContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public ListingRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void goToLDP(ListingDataModel listingDataModel) {
        ListingDetailFragment fragment = ListingDetailFragment.newInstance();
        fragment.setListing(listingDataModel);
        navigator.changeScreen(fragment);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
