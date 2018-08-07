package com.pubbix.feature.onboarding.registration;

import android.graphics.Bitmap;
import android.net.Uri;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.data.model.Credentials;
import com.pubbix.data.model.Profile;
import com.pubbix.data.model.User;

public interface RegistrationContract {

    interface View extends BaseView {
        String getFullName();

        void setFullName(String fullName);

        String getEmail();

        void showProgressDialog(String message);

        void setDoneImeOptionsOnEmail();

        void setEmail(String email);

        void setFullNameInputLayoutErrorMessage(final String errorMessage);

        void setEmailInputLayoutErrorMessage(final String errorMessage);

        void setImageBitmap(Bitmap bitmap);
    }

    interface Router extends BaseRouter {
        void goBack();

        void goToHomepage(RegistrationFragment registrationFragment);

    }

    interface Presenter {
        void onBackButtonClicked();

        void onProfilePictureSelected();

        void onNextButtonClicked();

        void registerUser(String phoneNumber);

        void onSuccessfulUserRegistration();

        void handleImagePickerResponse(Uri path);

        void handleExistingUser();
    }

    interface Interactor {
        void setPresenter(Presenter presenter);

        void registerUser(Credentials credentials);

        void onRegisterUserSuccess(Profile profile);

        void onRegisterUserFailure(Throwable throwable);
    }

    interface Repository {
        void setInteractor(Interactor interactor);

        void registerUser(Credentials credentials);

        void saveUserPref(User user);
    }
}
