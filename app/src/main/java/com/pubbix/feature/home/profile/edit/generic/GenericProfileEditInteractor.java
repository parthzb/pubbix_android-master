package com.pubbix.feature.home.profile.edit.generic;

import com.pubbix.base.BaseInteractor;
import com.pubbix.data.model.Profile;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

import timber.log.Timber;

@ScreenScope
public class GenericProfileEditInteractor extends BaseInteractor<GenericProfileEditContract.Repository>
        implements GenericProfileEditContract.Interactor {
    private GenericProfileEditContract.Presenter presenter;
    private GenericProfileEditContract.Repository repository;

    @Inject
    GenericProfileEditInteractor(GenericProfileEditContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void onUpdateUserSuccess(Profile profile) {
        presenter.onUserAccountUpdated();
    }

    @Override
    public void onUpdateUserFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public void setPresenter(GenericProfileEditContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onUpdateFullName(String newValue) {
        repository.updateFullName(newValue);
    }

    @Override
    public void onUpdateEmail(String newValue) {
        repository.updateEmail(newValue);
    }

    @Override
    public void onUpdatePhoneNumber(String newValue) {
        repository.updatePhone(newValue);
    }

    @Override
    public void onUpdateFoundation(String newValue) {
        repository.updateFoundation(newValue);
    }

    @Override
    public void onUpdateBioDescription(String newValue) {
        repository.updateBioDescription(newValue);
    }

    @Override
    public void onUpdateGender(int gender) {
        repository.updateGender(gender);
    }
}
