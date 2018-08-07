package com.pubbix.feature.onboarding.login;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        LoginScreenModule.class
})
public interface LoginComponent extends ScreenComponent<LoginFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginFragment> {

    }
}
