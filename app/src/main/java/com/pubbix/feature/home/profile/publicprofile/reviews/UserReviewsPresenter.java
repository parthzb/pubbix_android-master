package com.pubbix.feature.home.profile.publicprofile.reviews;

import android.content.Context;

import com.pubbix.base.BasePresenter;
import com.pubbix.di.ScreenScope;
import com.pubbix.util.UserHelper;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@ScreenScope
public class UserReviewsPresenter extends BasePresenter<UserReviewsFragment, UserReviewsContract.Interactor>
        implements UserReviewsContract.Presenter {
    private UserReviewsFragment view;
    private UserReviewsContract.Router router;
    private UserReviewsContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;

    @Inject
    public UserReviewsPresenter(@NotNull UserReviewsFragment profileFragment,
                                @NotNull UserReviewsContract.Router router,
                                @NotNull UserReviewsContract.Interactor interactor,
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
