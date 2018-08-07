package com.pubbix.feature.home.profile.publicprofile.reviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pubbix.R;
import com.pubbix.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UserReviewsFragment extends BaseFragment<UserReviewsPresenter>
        implements UserReviewsContract.View {

    public static final String TAG = UserReviewsFragment.class.getSimpleName();

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new UserReviewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
