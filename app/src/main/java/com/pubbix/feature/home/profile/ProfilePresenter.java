package com.pubbix.feature.home.profile;

import android.content.Context;

import com.facebook.accountkit.AccountKit;
import com.facebook.login.LoginManager;
import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.home.profile.datamodel.UserFullNameProfilePictureDataModel;
import com.pubbix.feature.home.profile.viewmodel.UserFullNameProfilePictureViewModel;
import com.pubbix.util.UserHelper;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@ScreenScope
public class ProfilePresenter extends BasePresenter<ProfileFragment, ProfileContract.Interactor>
        implements ProfileContract.Presenter {
    private ProfileFragment view;
    private ProfileContract.Router router;
    private ProfileContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;

    @Inject
    public ProfilePresenter(@NotNull ProfileFragment profileFragment,
                            @NotNull ProfileContract.Router router,
                            @NotNull ProfileContract.Interactor interactor,
                            @NotNull UserHelper userHelper) {
        view = profileFragment;
        context = view.getContext();
        this.router = router;
        this.router.setContext(context);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.userHelper = userHelper;
    }

    @Override
    public void setUpViewModels() {
        UserFullNameProfilePictureDataModel dataModel = userHelper.isLoggedIn() ?
                new UserFullNameProfilePictureDataModel(userHelper.getUser()) : new UserFullNameProfilePictureDataModel();
        view.addUserFullNameAndProfilePicture(new UserFullNameProfilePictureViewModel(dataModel,
                this, view.getResources(), userHelper));
        if (userHelper.isLoggedIn()) {
            view.addPublishListingBrick();
            view.addManageNotificationsBrick();
            view.addManageCategoriesBrick();
            view.addShareWithFriendBrick();
            view.addTermsAndConditions();
            view.addAboutUsBrick();
            view.addLogoutBrick();

        } else {
            view.addShareWithFriendBrick();
            view.addTermsAndConditions();
            view.addAboutUsBrick();
            view.addLoginBrick();
        }
    }

    @Override
    public void onViewEditProfileClicked() {
        if(userHelper.isLoggedIn()){
            router.goToPublicProfile();
        }else {
            logUserIn();
        }
    }

    @Override
    public void onPublishListingClicked() {
        router.goToPublishListing();
    }

    @Override
    public void onAboutUsClicked() {
        router.goToAboutUs();
    }

    @Override
    public void onManageNotificationClicked() {
        router.goToManageNotification();
    }

    @Override
    public void onManagePreferencesClicked() {
        router.goToManagePreferences();
    }

    @Override
    public void onShareWithFriendsClicked() {
        router.shareWithFriends();
    }

    @Override
    public void onTermsAndConditions() {
        router.goTermsAndConditions();
    }

    @Override
    public void logUserIn() {
        router.goToLoginScreen();
    }

    @Override
    public void logUserOut() {
        LoginManager.getInstance().logOut();
        AccountKit.logOut();
        userHelper.logUserOff();
        router.goToLoginScreen();
    }
}
