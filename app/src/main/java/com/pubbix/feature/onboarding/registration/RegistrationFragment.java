package com.pubbix.feature.onboarding.registration;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.databinding.FragmentRegistrationBinding;
import com.pubbix.feature.onboarding.registration.viewmodel.RegistrationViewModel;
import com.pubbix.util.Tools;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

public class RegistrationFragment extends BaseFragment<RegistrationPresenter> implements RegistrationContract.View {

    static int APP_REQUEST_CODE = 99;
    static final int IMAGE_REQUEST = 2;

    private FragmentRegistrationBinding mBinding;

    public static RegistrationFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_registration,
                container,
                false);
        mBinding.setViewModel(new RegistrationViewModel(presenter, getResources()));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDoneImeOptionsOnEmail();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    //Handle Returning User
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                            // Get Account Kit ID
                            String accountKitId = account.getId();
                            Timber.i("Account Kit Id: " + accountKitId);

                            if (account.getPhoneNumber() != null) {
                                Timber.i("CountryCode", "" + account.getPhoneNumber().getCountryCode());
                                Timber.i("PhoneNumber", "" + account.getPhoneNumber().getPhoneNumber());

                                // Get phone number
                                String phoneNumber = account.getPhoneNumber().toString();
                                presenter.registerUser(phoneNumber);
                            }
                        }

                        @Override
                        public void onError(final AccountKitError error) {
                            // Handle Error
                            Timber.e(error.toString());
                        }
                    });
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0, 10));
                }
            }
            // Surface the result to your user in an appropriate way.
            showMessage(toastMessage);
        }else if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            presenter.handleImagePickerResponse(path);
        }

    }

    @Override
    public String getFullName() {
        return mBinding.fullNameField.getText().toString();
    }

    @Override
    public void setFullName(String fullName) {
        mBinding.fullNameField.setText(fullName);
    }

    @Override
    public String getEmail() {
        return mBinding.emailField.getText().toString();
    }

    @Override
    public void showProgressDialog(String message) {

    }

    @Override
    public void setDoneImeOptionsOnEmail() {
        mBinding.emailField.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.onNextButtonClicked();
                handled = true;
            }
            return handled;
        });
    }

    @Override
    public void setEmail(String email) {
        mBinding.emailField.setText(email);
    }

    @Override
    public void setFullNameInputLayoutErrorMessage(String errorMessage) {
        if (!Tools.isNullOrBlank(errorMessage)) {
            mBinding.inputLayoutFullName.setError(errorMessage);
        } else {
            mBinding.inputLayoutFullName.setErrorEnabled(false);
        }
    }

    @Override
    public void setEmailInputLayoutErrorMessage(String errorMessage) {
        if (!Tools.isNullOrBlank(errorMessage)) {
            mBinding.inputLayoutEmail.setError(errorMessage);
        } else {
            mBinding.inputLayoutEmail.setErrorEnabled(false);
        }
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        mBinding.profileImage.setImageBitmap(bitmap);
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
