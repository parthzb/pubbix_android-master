package com.pubbix.feature.home.listingdetail;

import com.pubbix.base.BaseRepository;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class ListingDetailRepository extends BaseRepository<ListingDetailContract.Interactor>
        implements ListingDetailContract.Repository {
    private ListingDetailContract.Interactor interactor;

    @Inject
    ListingDetailRepository() {
    }

    @Override
    public void setInteractor(ListingDetailContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
