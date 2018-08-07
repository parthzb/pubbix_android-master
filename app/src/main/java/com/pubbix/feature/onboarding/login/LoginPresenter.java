package com.pubbix.feature.onboarding.login;

import android.content.Context;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.pubbix.R;
import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;

import java.util.Collections;

import javax.inject.Inject;

import timber.log.Timber;

@ScreenScope
public class LoginPresenter extends BasePresenter<LoginFragment, LoginContract.Interactor>
        implements LoginContract.Presenter {
    private LoginManager mLoginManager;
    private LoginFragment view;
    private LoginContract.Router router;
    private LoginContract.Interactor interactor;

    private Context mContext;
    private AccessTokenTracker mAccessTokenTracker;
    static int APP_REQUEST_CODE = 99;

    enum AuthenticationType {
        EMAIL,
        PHONE,
        FACEBOOK
    }

    @Inject
    public LoginPresenter(LoginFragment loginFragment, LoginContract.Interactor interactor, LoginRouter router) {
        view = loginFragment;
        mContext = view.getContext();
        this.interactor = interactor;
        this.router = router;
        this.interactor.setPresenter(this);
    }

    @Override
    public void onFacebookButtonClicked() {
        if (AccessToken.getCurrentAccessToken() != null) {
            mLoginManager.logOut();
        } else {
            mAccessTokenTracker.startTracking();
            mLoginManager.logInWithReadPermissions(view, Collections.singletonList("public_profile"));
        }
    }

    @Override
    public void onAccountKitLoginByPhoneClicked() {
        final Intent intent = new Intent(mContext, AccountKitActivity.class);
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

    @Override
    public void onAccountKitLoginByEmailClicked() {
        final Intent intent = new Intent(mContext, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.EMAIL,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        view.startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    public void onRegisterButtonClicked() {
        router.goToRegistration();
    }

    @Override
    public void onHomepageButtonClicked() {
        router.goToHomepage(view);
    }

    @Override
    public void setFacebookConnect(CallbackManager callbackManager) {
        // This should normally be on your application class
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                updateLoginByFacebookButton();
            }
        };

        mLoginManager = LoginManager.getInstance();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                updateLoginByFacebookButton();
                interactor.isReturningUser(AuthenticationType.FACEBOOK, loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                view.showMessage(mContext.getString(R.string.login_via_facebook_cancelled));
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        updateLoginByFacebookButton();
    }

    @Override
    public void handleNewUser() {
        final String message = mContext.getString(R.string.user_not_found_based_on_provided_information);
        view.showMessage(message);
        router.goToRegistration();
    }

    @Override
    public void handleReturningUser() {
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
                    final String phoneNumber = account.getPhoneNumber().toString();
                    Timber.i("NumberString", phoneNumber);

                    interactor.isReturningUser(AuthenticationType.PHONE, phoneNumber);
                }

                if (account.getEmail() != null) {
                    Timber.i("Email", account.getEmail());
                    final String email = account.getEmail();
                    interactor.isReturningUser(AuthenticationType.EMAIL, email);
                }
            }

            @Override
            public void onError(final AccountKitError error) {
                // Handle Error
                Timber.e(error.toString());
            }
        });
    }

    private void updateLoginByFacebookButton() {
        if (AccessToken.getCurrentAccessToken() != null) {
            view.setLoginByFacebookButtonText(mContext.getResources().getString(R.string.logout));
        } else {
            view.setLoginByFacebookButtonText(mContext.getResources().getString(R.string.login_via_facebook));
        }
    }
}
