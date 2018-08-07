package com.pubbix.feature.onboarding.registration;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        RegistrationScreenModule.class
})
public interface RegistrationComponent extends ScreenComponent<RegistrationFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RegistrationFragment> {

    }
}
