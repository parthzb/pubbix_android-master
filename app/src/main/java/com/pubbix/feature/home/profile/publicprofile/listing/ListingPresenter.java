package com.pubbix.feature.home.profile.publicprofile.listing;

import android.content.Context;

import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.feature.common.viewmodel.ListingViewModel;
import com.pubbix.util.UserHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

@ScreenScope
public class ListingPresenter extends BasePresenter<ListingFragment, ListingContract.Interactor>
        implements ListingContract.Presenter {
    private ListingFragment view;
    private ListingContract.Router router;
    private ListingContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;

    @Inject
    public ListingPresenter(@NotNull ListingFragment profileFragment,
                            @NotNull ListingContract.Router router,
                            @NotNull ListingContract.Interactor interactor,
                            @NotNull UserHelper userHelper) {
        view = profileFragment;
        context = view.getContext();
        this.router = router;
        this.router.setContext(context);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.userHelper = userHelper;
    }

    @Override
    public void fetchPublishedListings(String userId, Enums.ListingType listingType) {
        interactor.fetchListings(userId, listingType);
    }

    @Override
    public void render(List<ListingDataModel> dataModels) {
        int colorIndex = 0;
        for (ListingDataModel dataModel : dataModels) {
            view.addListingCardViewModel(new ListingViewModel(dataModel, colorIndex++, view.getResources(),
                    context, interactor));
        }
    }
}
