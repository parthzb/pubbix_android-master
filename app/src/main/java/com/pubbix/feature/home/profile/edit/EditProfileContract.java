package com.pubbix.feature.home.profile.edit;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.data.model.Address;
import com.pubbix.data.model.User;
import com.pubbix.feature.common.Enums.EditTextType;
import com.pubbix.feature.home.profile.viewmodel.EditProfileViewModel;


public interface EditProfileContract {
    interface View extends BaseView {
        void addViewModel(EditProfileViewModel editProfileViewModel);
    }

    interface Router extends BaseRouter {
        void setContext(Context context);

        void goToGenericProfileEdit(EditTextType type, String initialValue);

        void goBack();

        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void onRetrieveUser(User user);

        void updateProfileImage(String currentValue);

        void updateLocation(Address currentValue);

        void updateBirthday(String currentValue);

        void onProfileImageClicked();

        void onFullNameClicked(String currentValue);

        void onEmailClicked(String currentValue);

        void onPhoneNumber(String currentValue);

        void onLocationClicked();

        void onBirthdayClicked();

        void onFoundationClicked(String currentValue);

        void onGenderClicked(String currentValue);

        void onBioDescriptionClicked(String currentValue);

        void onBackButtonClicked();

        void fetchUser();

        void onProfilePictureUpdated(User user);

        void onUserAccountUpdated();

        void onLocationUpdated(User user);

        void onBirthdayUpdated(User user);
    }

    interface Interactor {
        void fetchUser(String userId);

        void updateProfileImage(String userId, String profileImage);

        void updateLocation(Address updatedLocation);

        void updateBirthday(String userId, String updatedBirthday);

        void onProfilePictureUpdatedSuccess(User user);

        void onBirthdayUpdatedSuccess(User user);

        void onLocationUpdatedSuccess(User user);

        void onUserRetrievedSuccess(User user);

        void onUserRetrievedFailure(Throwable throwable);

        void setPresenter(Presenter presenter);
    }

    interface Repository {
        void fetchUser(String userId);

        void updateUser(User user);

        void updateProfileImage(String userId, String profileImage);

        void updateLocation(Address updatedLocation);

        void updateBirthday(String userId, String updatedBirthday);

        void setInteractor(Interactor interactor);
    }
}
