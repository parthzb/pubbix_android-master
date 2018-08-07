package com.pubbix.feature.home.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pubbix.BR;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.feature.common.viewmodel.ListingViewModel;
import com.pubbix.feature.home.homepage.brick.CategoryCarouselBrick;
import com.pubbix.feature.home.homepage.viewmodel.HomepageHeaderViewModel;
import com.pubbix.util.brick.FullWidthBrickSize;
import com.pubbix.util.brick.HalfWidthBrickSize;
import com.wayfair.brickkit.brick.ViewModelBrick;
import com.wayfair.brickkit.padding.SimpleBrickPadding;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HomepageFragment extends BaseFragment<HomepagePresenter> implements HomepageContract.View {

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new HomepageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        dataManager.setRecyclerView(getContext(), view.findViewById(R.id.recycler_view),
                OrientationHelper.VERTICAL, false, view);
        //dataManager.getRecyclerView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!presenter.isInitialized()){
            presenter.setUpViewModels();
            presenter.fetchListings();
        }
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    public void addCategoryHeader(HomepageHeaderViewModel viewModel) {
        dataManager.addLast(new ViewModelBrick.Builder(R.layout.brick_header)
                .setSpanSize(new FullWidthBrickSize())
                .setPadding(new SimpleBrickPadding(8))
                .addViewModel(BR.viewModel, viewModel)
                .build());
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
    public void addMainCarousel() {
        dataManager.addLast(new CategoryCarouselBrick());
    }

    @Override
    public void onPause() {
        super.onPause();
        dataManager.clear();
    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return true;
    }
}
