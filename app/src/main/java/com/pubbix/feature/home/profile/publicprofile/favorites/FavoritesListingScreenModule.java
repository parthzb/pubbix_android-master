package com.pubbix.feature.home.profile.publicprofile.favorites;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class FavoritesListingScreenModule {
    @Binds
    abstract FavoritesListingContract.Presenter providesFavoritesListingPresenter(FavoritesListingPresenter presenter);

    @Binds
    abstract FavoritesListingContract.Interactor providesFavoritesListingInteractor(FavoritesListingInteractor interactor);

    @Binds
    abstract FavoritesListingContract.Repository providesFavoritesListingRepository(FavoritesListingRepository repository);

    @Binds
    abstract FavoritesListingContract.Router providesFavoritesListingRouter(FavoritesListingRouter router);
}
