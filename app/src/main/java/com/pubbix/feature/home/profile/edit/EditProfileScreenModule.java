package com.pubbix.feature.home.profile.edit;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class EditProfileScreenModule {
    @Binds
    abstract EditProfileContract.Presenter providesPublishedListingPresenter(EditProfilePresenter presenter);

    @Binds
    abstract EditProfileContract.Interactor providesPublishedListingInteractor(EditProfileInteractor interactor);

    @Binds
    abstract EditProfileContract.Repository providesPublishedListingRepository(EditProfileRepository repository);

    @Binds
    abstract EditProfileContract.Router providesPublishedListingRouter(EditProfileRouter router);
}
