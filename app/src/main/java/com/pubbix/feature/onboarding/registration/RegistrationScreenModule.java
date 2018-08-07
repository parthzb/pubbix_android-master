package com.pubbix.feature.onboarding.registration;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RegistrationScreenModule {
    @Binds
    abstract RegistrationContract.Presenter providesRegistrationPresenter(RegistrationPresenter presenter);

    @Binds
    abstract RegistrationContract.Interactor providesRegistrationInteractor(RegistrationInteractor interactor);

    @Binds
    abstract RegistrationContract.Repository providesRegistrationRepository(RegistrationRepository repository);

    @Binds
    abstract RegistrationContract.Router providesRegistrationRouter(RegistrationRouter router);
}
