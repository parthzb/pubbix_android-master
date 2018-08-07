package com.pubbix.feature.home.profile.publicprofile.reviews;

import com.pubbix.base.BaseRepository;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class UserReviewsRepository extends BaseRepository<UserReviewsContract.Interactor>
        implements UserReviewsContract.Repository {
    private UserReviewsContract.Interactor interactor;

    @Inject
    UserReviewsRepository() {
    }

    @Override
    public void setInteractor(UserReviewsContract.Interactor interactor) {
        this.interactor = interactor;
    }
}
