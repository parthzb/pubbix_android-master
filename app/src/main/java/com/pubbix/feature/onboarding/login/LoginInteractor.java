package com.pubbix.feature.onboarding.login;

import com.pubbix.base.BaseInteractor;
import com.pubbix.data.model.Profile;
import com.pubbix.util.NetworkUtil;

import javax.inject.Inject;

import timber.log.Timber;

public class LoginInteractor extends BaseInteractor<LoginContract.Repository>
        implements LoginContract.Interactor {
    private LoginContract.Presenter presenter;
    private LoginContract.Repository repository;

    @Inject
    LoginInteractor(LoginContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void isReturningUser(LoginPresenter.AuthenticationType authenticationType, String identifier) {
        switch (authenticationType) {
            case FACEBOOK:
                repository.retrieveUserByFacebookId(identifier);
                break;
            case PHONE:
                repository.retrieveUserByPhone(identifier);
                break;
            case EMAIL:
                repository.retrieveUserByEmail(identifier);
                break;
        }

    }

    @Override
    public void onRetrieveUserByFacebookIdSuccess(Profile profile) {
        repository.setUserLoginStatus(profile.getUser());
        presenter.onHomepageButtonClicked();
    }

    @Override
    public void onRetrieveUserByPhoneSuccess(Profile profile) {
        repository.setUserLoginStatus(profile.getUser());
        presenter.onHomepageButtonClicked();
    }

    @Override
    public void onRetrieveUserByEmailSuccess(Profile profile) {
        repository.setUserLoginStatus(profile.getUser());
        presenter.onHomepageButtonClicked();
    }

    @Override
    public void onRetrieveUserFailure(Throwable throwable) {
        if (NetworkUtil.isHttpStatusCode(throwable, NetworkUtil.NOT_FOUND)) {
            presenter.handleNewUser();
        }
        Timber.e(throwable);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
