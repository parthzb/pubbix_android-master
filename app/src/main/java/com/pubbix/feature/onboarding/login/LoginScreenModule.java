package com.pubbix.feature.onboarding.login;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginScreenModule {
    @Binds
    abstract LoginContract.Presenter providesLoginPresenter(LoginPresenter presenter);

    @Binds
    abstract LoginContract.Interactor providesLoginInteractor(LoginInteractor interactor);

    @Binds
    abstract LoginContract.Repository providesLoginRepository(LoginRepository repository);
}
