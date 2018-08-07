package com.pubbix.feature.home.profile;

import com.pubbix.base.BaseRepository;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class ProfileRepository extends BaseRepository<ProfileContract.Interactor>
        implements ProfileContract.Repository {
    private ProfileContract.Interactor interactor;

    @Inject
    ProfileRepository() {
    }

    @Override
    public void setInteractor(ProfileContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
