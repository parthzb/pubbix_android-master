package com.pubbix.feature.home.profile.publicprofile;

import com.pubbix.base.BaseRepository;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class PublicProfileRepository extends BaseRepository<PublicProfileContract.Interactor>
        implements PublicProfileContract.Repository {
    private PublicProfileContract.Interactor interactor;

    @Inject
    PublicProfileRepository() {
    }

    @Override
    public void setInteractor(PublicProfileContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
