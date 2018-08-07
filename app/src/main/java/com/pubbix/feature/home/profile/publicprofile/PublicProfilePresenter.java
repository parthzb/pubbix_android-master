package com.pubbix.feature.home.profile.publicprofile;

import android.content.Context;

import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.home.profile.viewmodel.PublicProfileViewModel;
import com.pubbix.util.UserHelper;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@ScreenScope
public class PublicProfilePresenter extends BasePresenter<PublicProfileFragment, PublicProfileContract.Interactor>
        implements PublicProfileContract.Presenter {
    private PublicProfileFragment view;
    private PublicProfileContract.Router router;
    private PublicProfileContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;

    @Inject
    public PublicProfilePresenter(@NotNull PublicProfileFragment profileFragment,
                                  @NotNull PublicProfileContract.Router router,
                                  @NotNull PublicProfileContract.Interactor interactor,
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
    public void setUpViews() {
        if (userHelper != null && userHelper.isLoggedIn()) {
            view.setToolbarViews(userHelper.getUser());
            view.setPublicProfileViewModel(new PublicProfileViewModel(this, view.getResources(), userHelper));
        } else {
            router.goBack();
        }
    }

    @Override
    public void onEditButtonClicked() {
        router.goToEditPage();
    }

    @Override
    public void onBackButtonClicked() {
        router.goBack();
    }

}
