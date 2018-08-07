package com.pubbix.feature.home.profile.publicprofile.favorites;

import com.pubbix.base.BaseRepository;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class FavoritesListingRepository extends BaseRepository<FavoritesListingContract.Interactor>
        implements FavoritesListingContract.Repository {
    private FavoritesListingContract.Interactor interactor;

    @Inject
    FavoritesListingRepository() {
    }

    @Override
    public void setInteractor(FavoritesListingContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
