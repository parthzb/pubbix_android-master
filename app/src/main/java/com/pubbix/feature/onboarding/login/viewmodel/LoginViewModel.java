package com.pubbix.feature.onboarding.login.viewmodel;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.pubbix.R;
import com.pubbix.feature.onboarding.login.LoginContract;

public class LoginViewModel extends BaseObservable {
    private LoginContract.Presenter mPresenter;
    private Resources mResources;

    public LoginViewModel(LoginContract.Presenter presenter, Resources resources) {
        mPresenter = presenter;
        mResources = resources;
    }

    @Bindable
    public String getCompanyName() {
        return mResources.getString(R.string.pubbix);
    }

    @Bindable
    public String getCompanySlogan() {
        return mResources.getString(R.string.pubbix_slogan);
    }

    @Bindable
    public String getAuthenticateText() {
        return mResources.getString(R.string.login_authenticate_text);
    }

    @Bindable
    public String getLoginByFacebookText() {
        return mResources.getString(R.string.login_via_facebook);
    }

    @Bindable
    public View.OnClickListener getOnLoginWithFacebookClicked() {
        return view -> mPresenter.onFacebookButtonClicked();
    }

    @Bindable
    public String getAccountKitLoginByPhoneText() {
        return mResources.getString(R.string.log_in_phone_button);
    }

    @Bindable
    public View.OnClickListener getOnAccountKitLoginByPhoneClicked() {
        return view -> mPresenter.onAccountKitLoginByPhoneClicked();
    }

    @Bindable
    public String getAccountKitLoginByEmailText() {
        return mResources.getString(R.string.log_in_email_button);
    }

    @Bindable
    public View.OnClickListener getOnAccountKitLoginByEmailClicked() {
        return view -> mPresenter.onAccountKitLoginByEmailClicked();
    }

    @Bindable
    public String getOrRegisterText() {
        return mResources.getString(R.string.create_account);
    }

    @Bindable
    public String getSignUpButtonText() {
        return mResources.getString(R.string.sign_up);
    }

    @Bindable
    public View.OnClickListener getOnSignUpButtonClicked() {
        return view -> mPresenter.onRegisterButtonClicked();
    }

    @Bindable
    public String getTermsAndConditionsText() {
        return mResources.getString(R.string.usage_terms_and_conditions);
    }

    @Bindable
    public String getHomepageButtonText() {
        return mResources.getString(R.string.skip_to_homepage);
    }

    @Bindable
    public View.OnClickListener getOnHomepageButtonClicked() {
        return view -> mPresenter.onHomepageButtonClicked();
    }
}
