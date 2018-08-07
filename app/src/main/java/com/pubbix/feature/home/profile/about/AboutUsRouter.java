package com.pubbix.feature.home.profile.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.pubbix.R;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

import static com.pubbix.util.Tools.isAppInstalled;

@ScreenScope
public class AboutUsRouter implements AboutUsContract.Router {

    private Context context;

    private enum SocialMedia {
        FACEBOOK,
        TWITTER,
        INSTAGRAM,
        YOUTUBE,
        PLAY_STORE;
    }

    @Inject
    AboutUsRouter() {
    }


    @Override
    public void goToContactUsPage() {
        openURLinBrowser(context.getString(R.string.website_contact_url));
    }

    @Override
    public void goToWebsite() {
        openURLinBrowser(context.getString(R.string.website_url));
    }

    @Override
    public void goToFacebookPage() {
        openURLinBrowser(getPageURL(SocialMedia.FACEBOOK));
    }

    @Override
    public void goToTwitterPage() {
        openURLinBrowser(getPageURL(SocialMedia.TWITTER));
    }

    @Override
    public void goToInstagramPage() {
        openURLinBrowser(getPageURL(SocialMedia.INSTAGRAM));
    }

    @Override
    public void goToYoutubePage() {
        openURLinBrowser(getPageURL(SocialMedia.YOUTUBE));
    }

    @Override
    public void goToPlayStorePage() {
        openURLinBrowser(getPageURL(SocialMedia.PLAY_STORE));
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    private String getPageURL(SocialMedia socialMedia) {
        String appPackage = null;
        String appUri = null;
        String websiteUrl = null;

        switch (socialMedia) {
            case FACEBOOK:
                appPackage = "com.facebook.katana";
                appUri = "fb://page/" + context.getString(R.string.facebook_page_id);
                websiteUrl = "fb://facewebmodal/f?href=" + context.getString(R.string.facebook_url);
                break;
            case TWITTER:
                appPackage = "com.twitter.android";
                appUri = "twitter://user?screen_name=" + context.getString(R.string.twitter_screen_name);
                websiteUrl = "https://twitter.com/#!/" + context.getString(R.string.twitter_screen_name);
                break;
            case INSTAGRAM:
                appPackage = "com.instagram.android";
                appUri = "instagram://user?username=" + context.getString(R.string.instagram_user_name);
                websiteUrl = "http://instagram.com/_u/" + context.getString(R.string.instagram_user_name);
                break;
            case YOUTUBE:
                appPackage = "com.google.android.youtube";
                appUri = "vnd.youtube:oAkckUVmsg0";
                websiteUrl = "https://www.youtube.com/channel/UCWrxt243nlr663VPcssdEWw";
                break;
            case PLAY_STORE:
                appPackage = "com.android.vending";
                appUri = "market://details?id=com.guslistes";
                websiteUrl = "https://play.google.com/store/apps/details?id=com.guslistes";
                break;
        }

        if (isAppInstalled(context, appPackage)) {
            try {
                return appUri;
            } catch (Exception e) {
                return websiteUrl;
            }
        } else {
            return websiteUrl;
        }

    }

    private void openURLinBrowser(final String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

}
