package com.pubbix.feature.home.profile.edit.generic;

import com.pubbix.base.BaseRepository;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class GenericProfileEditRepository extends BaseRepository<GenericProfileEditContract.Interactor>
        implements GenericProfileEditContract.Repository {
    private GenericProfileEditContract.Interactor interactor;

    @Inject
    GenericProfileEditRepository() {
    }

    @Override
    public void updateFullName(String updatedValue) {
        pubbixApi.updateUserFullName(userHelper.getUser().getUserId(), updatedValue)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onUpdateUserSuccess), interactor::onUpdateUserFailure);
    }

    @Override
    public void updateEmail(String updatedValue) {
        pubbixApi.updateUserFullName(userHelper.getUser().getUserId(), updatedValue)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onUpdateUserSuccess), interactor::onUpdateUserFailure);
    }

    @Override
    public void updatePhone(String updatedValue) {
        pubbixApi.updateUserPhoneNumber(userHelper.getUser().getUserId(), updatedValue)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onUpdateUserSuccess), interactor::onUpdateUserFailure);
    }

    @Override
    public void updateFoundation(String updatedValue) {
        pubbixApi.updateUserCompanyIdentity(userHelper.getUser().getUserId(), updatedValue)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onUpdateUserSuccess), interactor::onUpdateUserFailure);
    }

    @Override
    public void updateGender(int gender) {
        pubbixApi.updateUserGender(userHelper.getUser().getUserId(), gender)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onUpdateUserSuccess), interactor::onUpdateUserFailure);
    }

    @Override
    public void updateBioDescription(String updatedValue) {
        pubbixApi.updateUserBiography(userHelper.getUser().getUserId(), updatedValue)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe((interactor::onUpdateUserSuccess), interactor::onUpdateUserFailure);
    }

    @Override
    public void setInteractor(GenericProfileEditContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
