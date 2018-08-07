package com.pubbix.feature.home.profile.publicprofile.reviews;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UserReviewsScreenModule {
    @Binds
    abstract UserReviewsContract.Presenter providesUserReviewsPresenter(UserReviewsPresenter presenter);

    @Binds
    abstract UserReviewsContract.Interactor providesUserReviewsInteractor(UserReviewsInteractor interactor);

    @Binds
    abstract UserReviewsContract.Repository providesUserReviewsRepository(UserReviewsRepository repository);

    @Binds
    abstract UserReviewsContract.Router providesUserReviewsRouter(UserReviewsRouter router);
}
