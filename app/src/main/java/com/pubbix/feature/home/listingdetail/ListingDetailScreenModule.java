package com.pubbix.feature.home.listingdetail;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ListingDetailScreenModule {
    @Binds
    abstract ListingDetailContract.Presenter providesFavoritesListingPresenter(ListingDetailPresenter presenter);

    @Binds
    abstract ListingDetailContract.Interactor providesFavoritesListingInteractor(ListingDetailInteractor interactor);

    @Binds
    abstract ListingDetailContract.Repository providesFavoritesListingRepository(ListingDetailRepository repository);

    @Binds
    abstract ListingDetailContract.Router providesFavoritesListingRouter(ListingDetailRouter router);
}
