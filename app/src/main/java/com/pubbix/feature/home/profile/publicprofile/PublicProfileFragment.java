package com.pubbix.feature.home.profile.publicprofile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.data.model.User;
import com.pubbix.databinding.FragmentPublicProfileBinding;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.home.profile.publicprofile.adapter.ViewPagerAdapter;
import com.pubbix.feature.home.profile.publicprofile.listing.ListingFragment;
import com.pubbix.feature.home.profile.publicprofile.reviews.UserReviewsFragment;
import com.pubbix.feature.home.profile.viewmodel.PublicProfileViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class PublicProfileFragment extends BaseFragment<PublicProfilePresenter>
        implements PublicProfileContract.View, AppBarLayout.OnOffsetChangedListener {

    public static final String TAG = PublicProfileFragment.class.getSimpleName();
    private FragmentPublicProfileBinding binding;
    private boolean isToolbarTitleVisible = true;
    private int scrollRange = -1;
    private ConstraintLayout constraintLayout;
    private ImageButton backButton;
    private CircleImageView profileImage;
    private TextView fullName;
    private TextView country;
    private ImageButton editButton;

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new PublicProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_public_profile,
                container,
                false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View customView = mInflater.inflate(R.layout.custom_toolbar_public_profile, binding.toolbar);

        initViewPager();
        bindCustomView(customView);

        binding.appBar.addOnOffsetChangedListener(this);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setUpViews();
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
        }
        if (scrollRange + verticalOffset == 0) {
            constraintLayout.setVisibility(View.VISIBLE);
            isToolbarTitleVisible = true;
        } else if (isToolbarTitleVisible) {
            constraintLayout.setVisibility(View.INVISIBLE);
            isToolbarTitleVisible = false;
        }
    }

    private void initViewPager() {
        setupViewPager(binding.htabViewpager);
        binding.htabTabs.setupWithViewPager(binding.htabViewpager);

        binding.htabTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.htabViewpager.setCurrentItem(tab.getPosition());
                Log.d(TAG, "onTabSelected: pos: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void bindCustomView(View customView) {
        constraintLayout = customView.findViewById(R.id.constraint_layout);
        constraintLayout.setVisibility(View.INVISIBLE);

        backButton = customView.findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> presenter.onBackButtonClicked());

        profileImage = customView.findViewById(R.id.profile_image);
        fullName = customView.findViewById(R.id.toolbar_title);
        country = customView.findViewById(R.id.country);

        editButton = customView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(view -> presenter.onEditButtonClicked());
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(ListingFragment.newInstance(Enums.ListingType.PUBLISHED), "Published");
        adapter.addFrag(ListingFragment.newInstance(Enums.ListingType.UNPUBLISHED), "Unpublished");
        //adapter.addFrag(FavoritesListingFragment.newInstance(), "Favorites");
        adapter.addFrag(UserReviewsFragment.newInstance(), "Reviews");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setToolbarViews(User user) {
        fullName.setText(user.getName());
        country.setText(user.getAddress());

        Glide.with(profileImage.getContext())
                .load(user.getProfileImage())
                .apply(RequestOptions.signatureOf(new ObjectKey(user.getProfilePictureLastModified())))
                .into(profileImage);

    }

    @Override
    public void setPublicProfileViewModel(PublicProfileViewModel publicProfileViewModel) {
        binding.setViewModel(publicProfileViewModel);
    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
