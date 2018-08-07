package com.pubbix.feature.home.profile.publicprofile.favorites;

import com.pubbix.base.BaseInteractor;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class FavoritesListingInteractor extends BaseInteractor<FavoritesListingContract.Repository>
implements FavoritesListingContract.Interactor{
    private FavoritesListingContract.Presenter presenter;
    private FavoritesListingContract.Repository repository;

    @Inject
    FavoritesListingInteractor(FavoritesListingContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void setPresenter(FavoritesListingContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
