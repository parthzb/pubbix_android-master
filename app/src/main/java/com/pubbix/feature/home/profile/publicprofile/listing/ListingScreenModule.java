package com.pubbix.feature.home.profile.publicprofile.listing;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ListingScreenModule {
    @Binds
    abstract ListingContract.Presenter providesPublishedListingPresenter(ListingPresenter presenter);

    @Binds
    abstract ListingContract.Interactor providesPublishedListingInteractor(ListingInteractor interactor);

    @Binds
    abstract ListingContract.Repository providesPublishedListingRepository(ListingRepository repository);

    @Binds
    abstract ListingContract.Router providesPublishedListingRouter(ListingRouter router);
}
