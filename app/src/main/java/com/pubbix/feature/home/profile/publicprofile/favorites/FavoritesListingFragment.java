package com.pubbix.feature.home.profile.publicprofile.favorites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pubbix.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class FavoritesListingFragment extends BaseFragment<FavoritesListingPresenter>
        implements FavoritesListingContract.View {

    public static final String TAG = FavoritesListingFragment.class.getSimpleName();

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new FavoritesListingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
