package com.pubbix.feature.home.profile.about;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AboutUsScreenModule {
    @Binds
    abstract AboutUsContract.Presenter providesAboutUsPresenter(AboutUsPresenter presenter);

    @Binds
    abstract AboutUsContract.Router providesAboutUsRouter(AboutUsRouter router);
}
