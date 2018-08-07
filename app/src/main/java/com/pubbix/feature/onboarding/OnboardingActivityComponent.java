package com.pubbix.feature.onboarding;

import com.pubbix.di.ActivityScope;
import com.pubbix.ui.ActivityViewInterceptorModule;
import com.pubbix.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        OnboardingScreenBindingModule.class,
        NavigationModule.class,
        ActivityViewInterceptorModule.class,
})
public interface OnboardingActivityComponent extends AndroidInjector<OnboardingActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<OnboardingActivity>{

    }
}
