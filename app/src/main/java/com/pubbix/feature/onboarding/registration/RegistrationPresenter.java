package com.pubbix.feature.onboarding.registration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Patterns;

import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.pubbix.R;
import com.pubbix.base.BasePresenter;
import com.pubbix.data.model.Credentials;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.onboarding.login.LoginContract;
import com.pubbix.util.Tools;

import java.io.IOException;

import javax.inject.Inject;

import timber.log.Timber;

import static com.pubbix.feature.onboarding.registration.RegistrationFragment.APP_REQUEST_CODE;
import static com.pubbix.feature.onboarding.registration.RegistrationFragment.IMAGE_REQUEST;

@ScreenScope
public class RegistrationPresenter extends BasePresenter<RegistrationFragment, LoginContract.Interactor>
        implements RegistrationContract.Presenter {
    private RegistrationContract.Interactor interactor;
    private RegistrationContract.Router router;
    private RegistrationFragment view;
    private Context context;
    private Bitmap bitmap;
    private String faceBookId;
    private String phoneNumber;

    @Inject
    public RegistrationPresenter(RegistrationContract.Interactor interactor,
                                 RegistrationContract.Router router,
                                 RegistrationFragment view) {
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.router = router;
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void onBackButtonClicked() {
        router.goBack();
    }

    @Override
    public void onProfilePictureSelected() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        view.startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onNextButtonClicked() {
        if (isUserInputValid(view.getFullName(), view.getEmail())) {
            verifyUserPhoneNumber();
        }
    }

    @Override
    public void registerUser(String phoneNumber) {
        Credentials credentials = new Credentials(view.getFullName(),
                view.getEmail(),
                phoneNumber,
                Tools.imageToString(bitmap),
                faceBookId);
        interactor.registerUser(credentials);
    }

    @Override
    public void onSuccessfulUserRegistration() {
        router.goToHomepage(view);
    }

    @Override
    public void handleImagePickerResponse(Uri path) {
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), path);
            view.setImageBitmap(bitmap);
        } catch (IOException e) {
            Timber.e(e);
        }
    }

    @Override
    public void handleExistingUser() {
        view.showMessage(context.getString(R.string.account_created_with_provided_information));
        router.goToHomepage(view);
    }

    private void verifyUserPhoneNumber() {
        final Intent intent = new Intent(context, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        view.startActivityForResult(intent, APP_REQUEST_CODE);
    }

    private boolean isUserInputValid(String fullName, String email) {
        boolean isValid = true;
        if (Tools.isNullOrBlank(fullName)) {
            view.setFullNameInputLayoutErrorMessage("Your full name name is required");
            isValid = false;
        } else {
            view.setFullNameInputLayoutErrorMessage("");
        }
        if (Tools.isNullOrBlank(email)) {
            view.setEmailInputLayoutErrorMessage("Your email is required");
            isValid = false;
        } else if (!isEmailValid(email)) {
            view.setEmailInputLayoutErrorMessage("Please provide a valid email format");
            isValid = false;
        } else {
            view.setEmailInputLayoutErrorMessage("");
        }
        return isValid;
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
