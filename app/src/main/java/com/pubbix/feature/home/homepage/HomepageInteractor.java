package com.pubbix.feature.home.homepage;

import com.pubbix.base.BaseInteractor;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.wayfair.brickkit.brick.DataModel;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class HomepageInteractor extends BaseInteractor<HomepageContract.Repository>
        implements HomepageContract.Interactor {
    private HomepageContract.Presenter presenter;
    private HomepageContract.Repository repository;
    private HomepageContract.Router router;

    @Inject
    public HomepageInteractor(HomepageContract.Repository repository,
                              HomepageContract.Router router) {
        this.repository = repository;
        this.repository.setInteractor(this);
        this.router = router;
    }

    @Override
    public void fetchListings() {
        repository.retrieveHomepageListings();
    }

    @Override
    public void onRetrieveListingsSuccess(List<DataModel> dataModel) {
        presenter.render(dataModel);
    }

    @Override
    public void onRetrieveListingsFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public void setPresenter(HomepageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onListingClicked(ListingDataModel listingDataModel) {
        router.goToListingDetailPage(listingDataModel);
    }
}
