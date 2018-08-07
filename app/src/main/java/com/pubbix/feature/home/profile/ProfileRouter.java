package com.pubbix.feature.home.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.pubbix.R;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.home.profile.about.AboutUsFragment;
import com.pubbix.feature.home.profile.publicprofile.PublicProfileFragment;
import com.pubbix.feature.onboarding.OnboardingActivity;
import com.pubbix.feature.preferences.PreferencesActivity;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

import static com.pubbix.feature.preferences.PreferencesActivity.CATEGORIES_FRAGMENT;
import static com.pubbix.feature.preferences.PreferencesActivity.FRAGMENT_KEY;
import static com.pubbix.feature.preferences.PreferencesActivity.NOTIFICATION_FRAGMENT;

@ScreenScope
public class ProfileRouter implements ProfileContract.Router {
    private DefaultScreenNavigator navigator;
    private Context context;

    @Inject
    public ProfileRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void goToPublishListing() {
        //Todo not yet implemented
    }

    @Override
    public void goToPublicProfile() {
        navigator.changeScreen(PublicProfileFragment.newInstance());
    }

    @Override
    public void goToAboutUs() {
        navigator.changeScreen(AboutUsFragment.newInstance());
    }

    @Override
    public void goToLoginScreen() {
        context.startActivity(new Intent(context, OnboardingActivity.class));
    }

    @Override
    public void goToManageNotification() {
        final Intent intent = new Intent(context, PreferencesActivity.class);
        intent.putExtra(FRAGMENT_KEY, NOTIFICATION_FRAGMENT);
        context.startActivity(intent);
    }

    @Override
    public void goToManagePreferences() {
        final Intent intent = new Intent(context, PreferencesActivity.class);
        intent.putExtra(FRAGMENT_KEY, CATEGORIES_FRAGMENT);
        context.startActivity(intent);
    }

    @Override
    public void shareWithFriends() {
        String shareBody = "You can download the latest pubbix app using this link ";
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Pubbix App download ");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Partagez Pubbix avec vos amis "));
    }

    @Override
    public void goTermsAndConditions() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.terms_and_conditions)));
        context.startActivity(intent);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
