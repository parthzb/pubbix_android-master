package com.pubbix.feature.home.listingdetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.home.listingdetail.adapters.CustomImagePagerAdapter;
import com.pubbix.util.listener.OnSwipeTouchListener;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class ListingDetailFragment extends BaseFragment<ListingDetailPresenter>
        implements ListingDetailContract.View {
    public static final String TAG = ListingDetailFragment.class.getSimpleName();
    private ListingDataModel listing;
    private CollapsingToolbarLayout collapsingToolbar;
    private StoriesProgressView stories;
    private ViewPager viewPager;
    private int counter = 0;
    private MapFragment mapFragment;


    public static ListingDetailFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        ListingDetailFragment fragment = new ListingDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_listing_detail, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> presenter.onBackPressed());

        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
            appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle("");
            initMapFragment(appCompatActivity);
        }

        initListingData(view);
        initCollapsingToolbar(view);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomImagePagerAdapter(getContext(), listing.images));
        stories = view.findViewById(R.id.stories);

        initStory(listing.images.size());

        return view;
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    private void initListingData(View view) {
        if (listing != null) {
            TextView titleTv = view.findViewById(R.id.listing_title);
            titleTv.setText(listing.title);

            TextView categoryTv = view.findViewById(R.id.listing_category);
            categoryTv.setText(listing.categoryName);

            TextView descriptionTv = view.findViewById(R.id.listing_description);
            descriptionTv.setText(listing.description);
        }
    }

    private void initCollapsingToolbar(View view) {
        AppBarLayout appbar = view.findViewById(R.id.appbar);
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
        appbar.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initStory(int storyCount) {
        stories.setStoriesCount(storyCount);
        stories.setStoryDuration(3000);
        stories.startStories();
        viewPager.setCurrentItem(0);

        stories.setStoriesListener(new StoriesProgressView.StoriesListener() {
            @Override
            public void onNext() {
                viewPager.setCurrentItem(++counter, true);
            }

            @Override
            public void onPrev() {
                if ((counter - 1) < 0) return;
                viewPager.setCurrentItem(--counter, true);

            }

            @Override
            public void onComplete() {
                stories.destroy();
                stories.invalidate();
                stories.setStoriesListener(null);

                initStory(storyCount);
            }
        });

        viewPager.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                if (viewPager.getCurrentItem() != storyCount - 1) stories.skip();
            }

            @Override
            public void onSwipeRight() {
                stories.reverse();
            }

        });
    }

    private void initMapFragment(AppCompatActivity appCompatActivity) {

        mapFragment = new MapFragment();
        Bundle b = new Bundle();
        b.putBoolean(MapFragment.ARGUMENT_MAP_TYPE_CHANGABLE, true);
        mapFragment.setArguments(b);
        mapFragment.setMapCallBack(new MapFragment.MapCallBack() {
            @Override
            public void callbackReady() {

                /*latitude = gps.getLatitude();
                longitude = gps.getLongitude();*/

                LatLng startLatLng = new LatLng(34.0825832, -118.4170864);


                //mapFragment.showMyLocation();
                mapFragment.showLocationFocus(startLatLng);
                mapFragment.generateMarkerFromResource(startLatLng, "", BitmapDescriptorFactory.fromResource(R.drawable.round));
            }
        });
        FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.replace(R.id.map, mapFragment);
        t.commit();

    }

    public void setListing(ListingDataModel listing) {
        this.listing = listing;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            presenter.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
