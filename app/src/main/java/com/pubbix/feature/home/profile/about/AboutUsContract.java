package com.pubbix.feature.home.profile.about;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;

public interface AboutUsContract {
    interface View extends BaseView {
        void addLogoBrick();

        void addMissionStatementBrick();

        void addAppVersionBrick();

        void addContactBrick();

        void addWebsiteBrick();

        void addFacebookBrick();

        void addTwitterBrick();

        void addInstagramBrick();

        void addYoutubeBrick();

        void addPlayStoreBrick();
    }

    interface Presenter {
        void setUpBricks();

        void onClickContactUs();

        void onClickAdvertiseWithUs();

        void onClickLikeUsOnFacebook();

        void onClickFollowUsOnTwitter();

        void onClickFollowUsInstagram();

        void onClickWatchUsOnYoutube();

        void onClickVisitPlayStore();
    }

    interface Router extends BaseRouter {
        void goToContactUsPage();

        void goToWebsite();

        void goToFacebookPage();

        void goToTwitterPage();

        void goToInstagramPage();

        void goToYoutubePage();

        void goToPlayStorePage();

        void setContext(Context context);
    }

    interface Interactor {

    }
}
