package com.pubbix.feature.home.profile.publicprofile.reviews;

import com.pubbix.base.BaseInteractor;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class UserReviewsInteractor extends BaseInteractor<UserReviewsContract.Repository>
implements UserReviewsContract.Interactor{
    private UserReviewsContract.Presenter presenter;
    private UserReviewsContract.Repository repository;

    @Inject
    UserReviewsInteractor(UserReviewsContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void setPresenter(UserReviewsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
