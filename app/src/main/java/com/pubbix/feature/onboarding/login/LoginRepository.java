package com.pubbix.feature.onboarding.login;

import com.pubbix.base.BaseRepository;
import com.pubbix.data.model.User;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class LoginRepository extends BaseRepository<LoginContract.Interactor> implements LoginContract.Repository {
    private LoginContract.Interactor interactor;

    @Inject
    LoginRepository() {
    }

    @Override
    public void retrieveUserByFacebookId(final String facebookId) {
        pubbixApi.retrieveUserByFacebook(facebookId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onRetrieveUserByFacebookIdSuccess), interactor::onRetrieveUserFailure);
    }

    @Override
    public void retrieveUserByPhone(final String phone) {
        pubbixApi.retrieveUserByPhone(phone)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onRetrieveUserByPhoneSuccess), interactor::onRetrieveUserFailure);
    }

    @Override
    public void retrieveUserByEmail(final String email) {
        pubbixApi.retrieveUserByEmail(email)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onRetrieveUserByEmailSuccess), interactor::onRetrieveUserFailure);
    }

    @Override
    public void setUserLoginStatus(User user) {
        userHelper.saveUser(user);
        userHelper.logUserIn();
    }

    @Override
    public void setInteractor(LoginContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
