package com.pubbix.feature.home.profile.publicprofile.listing;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.common.viewmodel.ListingViewModel;

import java.util.List;

public interface ListingContract {
    interface View extends BaseView {
        void addListingCardViewModel(ListingViewModel viewModel);
    }

    interface Router extends BaseRouter {
        void goToLDP(ListingDataModel listingDataModel);

        void setContext(Context context);
    }

    interface Presenter {
        void fetchPublishedListings(String userId, Enums.ListingType listingType);

        void render(List<ListingDataModel> dataModels);
    }

    interface Interactor extends ListingViewModel.Interactions {
        void fetchListings(String userId, Enums.ListingType listingType);

        void onFetchedListingsSuccess(List<ListingDataModel> dataModels);

        void onFetchedListingsFailure(Throwable throwable);

        void setPresenter(Presenter presenter);
    }

    interface Repository {
        void getPublishedListings(String userId);

        void getUnpublishedListings(String userId);

        void setInteractor(Interactor interactor);
    }
}
