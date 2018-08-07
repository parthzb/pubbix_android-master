package com.pubbix.feature.home.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.pubbix.BR;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.feature.home.profile.viewmodel.UserFullNameProfilePictureViewModel;
import com.pubbix.util.brick.DividerItemDecorator;
import com.pubbix.util.brick.FullWidthBrickSize;
import com.pubbix.util.brick.common.SelectableTextIconBrick;
import com.wayfair.brickkit.brick.ViewModelBrick;
import com.wayfair.brickkit.padding.InnerOuterBrickPadding;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import timber.log.Timber;

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements ProfileContract.View {

    private final InnerOuterBrickPadding padding = new InnerOuterBrickPadding(8, 0);
    private final static FullWidthBrickSize fullWidthBrickSize = new FullWidthBrickSize();


    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new ProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataManager.getRecyclerView().setPadding(32, 120, 32, 0);
        dataManager.getRecyclerView().setHorizontalScrollBarEnabled(false);
        dataManager.getRecyclerView().setVerticalScrollBarEnabled(false);
        dataManager.getRecyclerView().addItemDecoration(
                new DividerItemDecorator(ContextCompat.getDrawable(getContext(), R.drawable.divider)));
        presenter.setUpViewModels();
        Timber.d("Token " + FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    public void onPause() {
        super.onPause();
        dataManager.clear();
    }

    @Override
    public void addUserFullNameAndProfilePicture(UserFullNameProfilePictureViewModel viewModel) {
        ViewModelBrick viewModelBrick = new ViewModelBrick.Builder(R.layout.brick_user_fullname_profile_picture)
                .setSpanSize(fullWidthBrickSize)
                .addViewModel(BR.viewModel, viewModel)
                .build();
        dataManager.addLast(viewModelBrick);
    }

    @Override
    public void addPublishListingBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Publish a listing",
                R.drawable.ic_advert,
                view -> presenter.onPublishListingClicked()));
    }

    @Override
    public void addAboutUsBrick() {
        dataManager.addLast(new SelectableTextIconBrick("About Us",
                R.drawable.ic_help_outline_black_24dp,
                view -> presenter.onAboutUsClicked()));
    }

    @Override
    public void addLoginBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Log In",
                R.drawable.ic_power_settings_new_black_24dp,
                view -> presenter.logUserIn()));
    }

    @Override
    public void addLogoutBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Log Out",
                R.drawable.ic_power_settings_new_black_24dp,
                view -> presenter.logUserOut()));
    }

    @Override
    public void addShareWithFriendBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Invite your friends",
                R.drawable.ic_people_outline_black_24dp,
                view -> presenter.onShareWithFriendsClicked()));
    }

    @Override
    public void addManageNotificationsBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Manage your notifications",
                R.drawable.ic_notifications_none_black_24dp,
                view -> presenter.onManageNotificationClicked()));
    }

    @Override
    public void addManageCategoriesBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Manage your preferences",
                R.drawable.ic_widgets_black_24dp,
                view -> presenter.onManagePreferencesClicked()));
    }

    @Override
    public void addTermsAndConditions() {
        dataManager.addLast(new SelectableTextIconBrick("Our terms and conditions",
                R.drawable.ic_branding_watermark_black_24dp,
                view -> presenter.onTermsAndConditions()));
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return true;
    }
}
