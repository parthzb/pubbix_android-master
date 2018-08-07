package com.pubbix.feature.home.homepage;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomepageScreenModule {
    @Binds
    abstract HomepageContract.Presenter providesHomepagePresenter(HomepagePresenter presenter);

    @Binds
    abstract HomepageContract.Interactor providesHomepageInteractor(HomepageInteractor interactor);

    @Binds
    abstract HomepageContract.Repository providesHomepageRepository(HomepageRepository repository);

    @Binds
    abstract HomepageContract.Router providesHomepageRouter(HomepageRouter router);
}
