package com.pubbix.feature.home.profile.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.util.brick.DividerItemDecorator;
import com.pubbix.util.brick.common.ImageBrick;
import com.pubbix.util.brick.common.ParagraphBrick;
import com.pubbix.util.brick.common.SelectableTextIconBrick;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AboutUsFragment extends BaseFragment<AboutUsPresenter> implements AboutUsContract.View {
    public static AboutUsFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        AboutUsFragment fragment = new AboutUsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataManager.getRecyclerView().setPadding(32, 0, 32, 0);
        dataManager.getRecyclerView().setHorizontalScrollBarEnabled(false);
        dataManager.getRecyclerView().setVerticalScrollBarEnabled(false);
        dataManager.getRecyclerView().addItemDecoration(
                new DividerItemDecorator(ContextCompat.getDrawable(getContext(), R.drawable.divider), 2));
        presenter.setUpBricks();
    }

    @Override
    public void onPause() {
        super.onPause();
        dataManager.clear();
    }

    @Override
    public void addLogoBrick() {
        dataManager.addLast(new ImageBrick(R.mipmap.app_logo));
    }

    @Override
    public void addMissionStatementBrick() {
        dataManager.addLast(new ParagraphBrick(getString(R.string.mission_statement)));
    }

    @Override
    public void addAppVersionBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Version 1.0.0",
                R.drawable.ic_version_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> {

                }));
    }

    @Override
    public void addContactBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Contact us",
                R.drawable.ic_phone_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickContactUs()));
    }

    @Override
    public void addWebsiteBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Visit our website",
                R.drawable.ic_visit_website_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickAdvertiseWithUs()));
    }

    @Override
    public void addFacebookBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Like us on Facebook",
                R.drawable.ic_facebook_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickLikeUsOnFacebook()));
    }

    @Override
    public void addTwitterBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Follow us on Twitter",
                R.drawable.ic_twitter_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickFollowUsOnTwitter()));
    }

    @Override
    public void addInstagramBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Follow us on Instagram",
                R.drawable.ic_instagram_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickFollowUsInstagram()));
    }

    @Override
    public void addYoutubeBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Watch us on Youtube",
                R.drawable.ic_youtube_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickWatchUsOnYoutube()));
    }

    @Override
    public void addPlayStoreBrick() {
        dataManager.addLast(new SelectableTextIconBrick("Rate us on the Play Store",
                R.drawable.ic_google_play_32,
                SelectableTextIconBrick.IconPosition.LEFT,
                view -> presenter.onClickVisitPlayStore()));
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
