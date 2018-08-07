package com.pubbix.feature.home.profile.publicprofile.listing;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pubbix.BR;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.common.viewmodel.ListingViewModel;
import com.pubbix.util.UserHelper;
import com.pubbix.util.brick.HalfWidthBrickSize;
import com.wayfair.brickkit.brick.ViewModelBrick;
import com.wayfair.brickkit.padding.SimpleBrickPadding;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import javax.inject.Inject;

public class ListingFragment extends BaseFragment<ListingPresenter>
        implements ListingContract.View {

    public static final String TAG = ListingFragment.class.getSimpleName();
    @Inject
    UserHelper userHelper;
    private Enums.ListingType listingType;
    public static final String TYPE = "type";


    public static Fragment newInstance(Enums.ListingType listingType) {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        bundle.putSerializable(TYPE, listingType);
        Fragment fragment = new ListingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        if (bundle != null) {
            listingType = (Enums.ListingType) bundle.getSerializable(TYPE);
            presenter.fetchPublishedListings(userHelper.getUser().getUserId(), listingType);
        }
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    public void addListingCardViewModel(ListingViewModel viewModel) {
        dataManager.addLast(new ViewModelBrick.Builder(R.layout.brick_generic_listing_card)
                .setSpanSize(new HalfWidthBrickSize())
                .setPadding(new SimpleBrickPadding(8))
                .addViewModel(BR.viewModel, viewModel)
                .build());
    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
