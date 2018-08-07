package com.pubbix.feature.home.profile.publicprofile.listing;

import com.pubbix.base.BaseInteractor;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.common.datamodel.ListingDataModel;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@ScreenScope
public class ListingInteractor extends BaseInteractor<ListingContract.Repository>
        implements ListingContract.Interactor {
    private ListingContract.Presenter presenter;
    private ListingContract.Repository repository;
    private ListingContract.Router router;

    @Inject
    ListingInteractor(ListingContract.Repository repository,
                      ListingContract.Router router) {
        this.repository = repository;
        this.repository.setInteractor(this);
        this.router = router;
    }

    @Override
    public void fetchListings(String userId, Enums.ListingType listingType) {
        switch (listingType) {
            case PUBLISHED:
                repository.getPublishedListings(userId);
                break;
            case UNPUBLISHED:
                repository.getUnpublishedListings(userId);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFetchedListingsSuccess(List<ListingDataModel> dataModels) {
        presenter.render(dataModels);
    }

    @Override
    public void onFetchedListingsFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public void setPresenter(ListingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onListingClicked(ListingDataModel listingDataModel) {
    }
}
