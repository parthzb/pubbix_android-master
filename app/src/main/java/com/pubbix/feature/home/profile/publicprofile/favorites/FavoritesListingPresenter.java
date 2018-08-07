package com.pubbix.feature.home.profile.publicprofile.favorites;

import android.content.Context;

import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;
import com.pubbix.util.UserHelper;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@ScreenScope
public class FavoritesListingPresenter extends BasePresenter<FavoritesListingFragment, FavoritesListingContract.Interactor>
        implements FavoritesListingContract.Presenter {
    private FavoritesListingFragment view;
    private FavoritesListingContract.Router router;
    private FavoritesListingContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;

    @Inject
    public FavoritesListingPresenter(@NotNull FavoritesListingFragment profileFragment,
                                     @NotNull FavoritesListingContract.Router router,
                                     @NotNull FavoritesListingContract.Interactor interactor,
                                     @NotNull UserHelper userHelper) {
        view = profileFragment;
        context = view.getContext();
        this.router = router;
        this.router.setContext(context);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.userHelper = userHelper;
    }
}
