package com.pubbix.feature.home.profile.edit.generic;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GenericProfileEditScreenModule {
    @Binds
    abstract GenericProfileEditContract.Presenter providesGenericProfileEditPresenter(GenericProfileEditPresenter presenter);

    @Binds
    abstract GenericProfileEditContract.Interactor providesGenericProfileEditInteractor(GenericProfileEditInteractor interactor);

    @Binds
    abstract GenericProfileEditContract.Repository providesGenericProfileEditRepository(GenericProfileEditRepository repository);

    @Binds
    abstract GenericProfileEditContract.Router providesGenericProfileEditRouter(GenericProfileEditRouter router);
}
