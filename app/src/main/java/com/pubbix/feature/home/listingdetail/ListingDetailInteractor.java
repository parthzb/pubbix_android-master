package com.pubbix.feature.home.listingdetail;

import com.pubbix.base.BaseInteractor;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class ListingDetailInteractor extends BaseInteractor<ListingDetailContract.Repository>
implements ListingDetailContract.Interactor{
    private ListingDetailContract.Presenter presenter;
    private ListingDetailContract.Repository repository;

    @Inject
    ListingDetailInteractor(ListingDetailContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void setPresenter(ListingDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
