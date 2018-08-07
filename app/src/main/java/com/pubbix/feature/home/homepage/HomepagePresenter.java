package com.pubbix.feature.home.homepage;

import android.content.Context;

import com.pubbix.base.BasePresenter;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.common.viewmodel.ListingViewModel;
import com.pubbix.feature.home.homepage.datamodel.HomepageHeaderDataModel;
import com.pubbix.feature.home.homepage.viewmodel.HomepageHeaderViewModel;
import com.wayfair.brickkit.brick.DataModel;

import java.util.List;

import javax.inject.Inject;

public class HomepagePresenter extends BasePresenter<HomepageFragment, HomepageContract.Interactor>
        implements HomepageContract.Presenter {

    private HomepageFragment view;
    private HomepageContract.Router router;
    private HomepageContract.Interactor interactor;
    private Context context;
    private boolean isInitialized = false;


    @Inject
    HomepagePresenter(HomepageFragment homepageFragment, HomepageContract.Router router,
                      HomepageContract.Interactor interactor) {
        this.view = homepageFragment;
        this.context = view.getContext();
        this.router = router;
        this.interactor = interactor;
        this.interactor.setPresenter(this);
    }

    @Override
    public void setUpViewModels() {
        view.addCategoryHeader(new HomepageHeaderViewModel(
                new HomepageHeaderDataModel("What can we help you find, Auguste ?",
                        "")));
        view.addMainCarousel();
    }

    @Override
    public void render(List<DataModel> dataModels) {
        int colorIndex = 0;

        for (DataModel dataModel : dataModels) {
            if (dataModel instanceof HomepageHeaderDataModel) {
                view.addCategoryHeader(new HomepageHeaderViewModel((HomepageHeaderDataModel) dataModel));
            } else if (dataModel instanceof ListingDataModel) {
                ListingDataModel listingDataModel = (ListingDataModel) dataModel;
                view.addListingCardViewModel(new ListingViewModel(listingDataModel, colorIndex++,
                        view.getResources(),
                        context, interactor));

            }

        }

        isInitialized = true;
    }

    @Override
    public void fetchListings() {
        interactor.fetchListings();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void onBackPressed() {
        router.goBack();
    }
}
