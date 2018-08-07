package com.pubbix.feature.home.profile.edit;

import com.pubbix.base.BaseRepository;
import com.pubbix.data.model.Address;
import com.pubbix.data.model.Profile;
import com.pubbix.data.model.User;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class EditProfileRepository extends BaseRepository<EditProfileContract.Interactor>
        implements EditProfileContract.Repository {
    private EditProfileContract.Interactor interactor;

    @Inject
    EditProfileRepository() {
    }

    @Override
    public void fetchUser(String userId) {
        pubbixApi.getUserDetails(userId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map(Profile::getUser)
                .subscribe((interactor::onUserRetrievedSuccess), interactor::onUserRetrievedFailure);
    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            userHelper.saveUser(user);
        }
    }

    @Override
    public void updateProfileImage(String userId, String profileImage) {
        pubbixApi.updateProfileImage(userId, profileImage)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map(Profile::getUser)
                .subscribe((interactor::onProfilePictureUpdatedSuccess), interactor::onUserRetrievedFailure);
    }

    @Override
    public void updateLocation(Address updatedLocation) {
        pubbixApi.updateUserLocation(updatedLocation)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map(Profile::getUser)
                .subscribe((interactor::onLocationUpdatedSuccess), interactor::onUserRetrievedFailure);
    }

    @Override
    public void updateBirthday(String userId, String updatedBirthday) {
        pubbixApi.updateUserBirthday(userId, updatedBirthday)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map(Profile::getUser)
                .subscribe((interactor::onBirthdayUpdatedSuccess), interactor::onUserRetrievedFailure);
    }

    @Override
    public void setInteractor(EditProfileContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
