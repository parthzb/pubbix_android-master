package com.pubbix.feature.home.profile.publicprofile.listing;

import com.pubbix.base.BaseRepository;
import com.pubbix.data.model.Listings;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.datamodel.ListingDataModel;

import javax.inject.Inject;

@ScreenScope
public class ListingRepository extends BaseRepository<ListingContract.Interactor>
        implements ListingContract.Repository {
    private ListingContract.Interactor interactor;

    @Inject
    ListingRepository() {
    }

    @Override
    public void getPublishedListings(String userId) {
        pubbixApi.getUserPublishedListings(userId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .flatMapIterable(Listings::getListings)
                .map(ListingDataModel::new)
                .toList()
                .subscribe(interactor::onFetchedListingsSuccess,
                        interactor::onFetchedListingsFailure);
    }

    @Override
    public void getUnpublishedListings(String userId) {
        pubbixApi.getUserUnpublishedListings(userId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .flatMapIterable(Listings::getListings)
                .map(ListingDataModel::new)
                .toList()
                .subscribe(interactor::onFetchedListingsSuccess,
                        interactor::onFetchedListingsFailure);
    }

    @Override
    public void setInteractor(ListingContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
