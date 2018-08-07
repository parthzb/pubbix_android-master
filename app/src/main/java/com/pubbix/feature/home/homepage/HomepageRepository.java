package com.pubbix.feature.home.homepage;

import com.pubbix.base.BaseRepository;
import com.pubbix.data.model.Category;
import com.pubbix.data.model.Listing;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.home.homepage.datamodel.HomepageHeaderDataModel;
import com.wayfair.brickkit.brick.DataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomepageRepository extends BaseRepository<HomepageContract.Interactor>
        implements HomepageContract.Repository {
    private HomepageContract.Interactor interactor;

    @Inject
    public HomepageRepository() {
    }

    @Override
    public void retrieveHomepageListings() {
        pubbixApi.getHomepageListings()
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(homepageListings -> {
                            for (Category category : homepageListings.categories) {
                                interactor.onRetrieveListingsSuccess(generateDataModels(category));
                            }
                        },
                        interactor::onRetrieveListingsFailure);
    }

    private List<DataModel> generateDataModels(Category category) {
        List<DataModel> dataModels = new ArrayList<>();
        dataModels.add(new HomepageHeaderDataModel(category.callToAction, ""));

        //Add a null check
        for (Listing listing : category.listings) {
            dataModels.add(new ListingDataModel(listing));
        }

        return dataModels;
    }

    @Override
    public void setInteractor(HomepageContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
