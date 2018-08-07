package com.pubbix.feature.home.profile.publicprofile;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PublicProfileScreenModule {
    @Binds
    abstract PublicProfileContract.Presenter providesProfilePresenter(PublicProfilePresenter presenter);

    @Binds
    abstract PublicProfileContract.Interactor providesProfileInteractor(PublicProfileInteractor interactor);

    @Binds
    abstract PublicProfileContract.Repository providesProfileRepository(PublicProfileRepository repository);

    @Binds
    abstract PublicProfileContract.Router providesProfileRouter(PublicProfileRouter router);
}
