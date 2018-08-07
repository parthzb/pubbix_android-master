package com.pubbix.feature.home.listingdetail;

import android.content.Context;

import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;
import com.pubbix.util.UserHelper;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@ScreenScope
public class ListingDetailPresenter extends BasePresenter<ListingDetailFragment, ListingDetailContract.Interactor>
        implements ListingDetailContract.Presenter {
    private ListingDetailFragment view;
    private ListingDetailContract.Router router;
    private ListingDetailContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;

    @Inject
    public ListingDetailPresenter(@NotNull ListingDetailFragment profileFragment,
                                  @NotNull ListingDetailContract.Router router,
                                  @NotNull ListingDetailContract.Interactor interactor,
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
    public void onBackPressed() {
        router.goBack();
    }
}
