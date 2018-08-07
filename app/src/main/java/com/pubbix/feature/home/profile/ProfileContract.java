package com.pubbix.feature.home.profile;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.feature.home.profile.viewmodel.UserFullNameProfilePictureViewModel;

public interface ProfileContract {
    interface View extends BaseView {
        void addUserFullNameAndProfilePicture(UserFullNameProfilePictureViewModel viewModel);
        void addPublishListingBrick();
        void addAboutUsBrick();
        void addLoginBrick();
        void addLogoutBrick();
        void addShareWithFriendBrick();
        void addManageNotificationsBrick();
        void addManageCategoriesBrick();
        void addTermsAndConditions();
    }

    interface Router extends BaseRouter {
        void goToPublishListing();
        void goToPublicProfile();
        void goToAboutUs();
        void goToLoginScreen();
        void goToManageNotification();
        void goToManagePreferences();
        void shareWithFriends();
        void goTermsAndConditions();
        void setContext(Context context);
    }

    interface Presenter {
        void setUpViewModels();
        void onViewEditProfileClicked();
        void onPublishListingClicked();
        void onAboutUsClicked();
        void onManageNotificationClicked();
        void onManagePreferencesClicked();
        void onShareWithFriendsClicked();
        void onTermsAndConditions();
        void logUserIn();
        void logUserOut();
    }

    interface Interactor {
        void setPresenter(Presenter presenter);
    }

    interface Repository{
        void setInteractor(Interactor interactor);
    }
}
