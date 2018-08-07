package com.pubbix.feature.home.profile;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProfileScreenModule {
    @Binds
    abstract ProfileContract.Presenter providesProfilePresenter(ProfilePresenter presenter);

    @Binds
    abstract ProfileContract.Interactor providesProfileInteractor(ProfileInteractor interactor);

    @Binds
    abstract ProfileContract.Repository providesProfileRepository(ProfileRepository repository);

    @Binds
    abstract ProfileContract.Router providesProfileRouter(ProfileRouter router);
}
