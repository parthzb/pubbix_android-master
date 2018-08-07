package com.pubbix.feature.home.profile.edit;

import com.pubbix.base.BaseInteractor;
import com.pubbix.data.model.Address;
import com.pubbix.data.model.User;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

import timber.log.Timber;

@ScreenScope
public class EditProfileInteractor extends BaseInteractor<EditProfileContract.Repository>
        implements EditProfileContract.Interactor {
    private EditProfileContract.Presenter presenter;
    private EditProfileContract.Repository repository;

    @Inject
    EditProfileInteractor(EditProfileContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void fetchUser(String userId) {
        repository.fetchUser(userId);
    }

    @Override
    public void updateProfileImage(String userId, String profileImage) {
        repository.updateProfileImage(userId, profileImage);
    }

    @Override
    public void updateLocation(Address updatedLocation) {
        repository.updateLocation(updatedLocation);
    }

    @Override
    public void updateBirthday(String userId, String updatedBirthday) {
        repository.updateBirthday(userId, updatedBirthday);
    }

    @Override
    public void onProfilePictureUpdatedSuccess(User user) {
        presenter.onProfilePictureUpdated(user);
    }

    @Override
    public void onBirthdayUpdatedSuccess(User user) {
        presenter.onBirthdayUpdated(user);
    }

    @Override
    public void onLocationUpdatedSuccess(User user) {
        presenter.onLocationUpdated(user);
    }

    @Override
    public void onUserRetrievedSuccess(User user) {
        repository.updateUser(user);
        presenter.onRetrieveUser(user);
    }

    @Override
    public void onUserRetrievedFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public void setPresenter(EditProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
