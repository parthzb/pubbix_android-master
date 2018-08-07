package com.pubbix.feature.home.homepage;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.common.viewmodel.ListingViewModel;
import com.pubbix.feature.home.homepage.viewmodel.HomepageHeaderViewModel;
import com.wayfair.brickkit.brick.DataModel;

import java.util.List;

public interface HomepageContract {
    interface View extends BaseView {
        void addCategoryHeader(HomepageHeaderViewModel viewModel);

        void addListingCardViewModel(ListingViewModel viewModel);

        void addMainCarousel();
    }

    interface Presenter {
        void setUpViewModels();

        void render(List<DataModel> dataModels);

        void fetchListings();

        boolean isInitialized();

        void onBackPressed();
    }

    interface Interactor extends ListingViewModel.Interactions {
        void fetchListings();

        void onRetrieveListingsSuccess(List<DataModel> dataModel);

        void onRetrieveListingsFailure(Throwable throwable);

        void setPresenter(Presenter presenter);
    }

    interface Repository {
        void retrieveHomepageListings();

        void setInteractor(Interactor interactor);
    }


    interface Router extends BaseRouter {
        void goBack();
        void goToListingDetailPage(ListingDataModel listingDataModel);
    }
}
