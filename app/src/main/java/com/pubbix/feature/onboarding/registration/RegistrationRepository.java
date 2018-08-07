package com.pubbix.feature.onboarding.registration;

import com.pubbix.base.BaseRepository;
import com.pubbix.data.model.Credentials;
import com.pubbix.data.model.User;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class RegistrationRepository extends BaseRepository<RegistrationContract.Interactor>
        implements RegistrationContract.Repository {
    private RegistrationContract.Interactor interactor;


    @Inject
    public RegistrationRepository() {
    }

    @Override
    public void setInteractor(RegistrationContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void registerUser(Credentials credentials) {
        pubbixApi.createUserAccount(credentials)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onRegisterUserSuccess), (interactor::onRegisterUserFailure));
    }

    @Override
    public void saveUserPref(User user) {
        userHelper.saveUser(user);
    }
}
