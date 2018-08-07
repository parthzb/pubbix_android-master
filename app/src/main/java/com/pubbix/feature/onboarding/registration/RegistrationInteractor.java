package com.pubbix.feature.onboarding.registration;

import com.pubbix.base.BaseInteractor;
import com.pubbix.data.model.Credentials;
import com.pubbix.data.model.Profile;
import com.pubbix.di.ScreenScope;
import com.pubbix.util.NetworkUtil;

import javax.inject.Inject;

import timber.log.Timber;

@ScreenScope
public class RegistrationInteractor extends BaseInteractor<RegistrationContract.Repository>
        implements RegistrationContract.Interactor {

    private RegistrationContract.Presenter presenter;
    private RegistrationContract.Repository repository;

    @Inject
    public RegistrationInteractor(RegistrationContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void registerUser(Credentials credentials) {
        repository.registerUser(credentials);
    }

    @Override
    public void onRegisterUserSuccess(Profile profile) {
        repository.saveUserPref(profile.getUser());
        presenter.onSuccessfulUserRegistration();
    }

    @Override
    public void onRegisterUserFailure(Throwable throwable) {
        if(NetworkUtil.isHttpStatusCode(throwable, NetworkUtil.CONFLICT)){
            presenter.handleExistingUser();
        }
        Timber.e(throwable);
    }
}
