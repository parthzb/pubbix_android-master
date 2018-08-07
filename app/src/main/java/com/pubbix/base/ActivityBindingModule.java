package com.pubbix.base;

import android.app.Activity;

import com.pubbix.feature.home.MainActivityComponent;
import com.pubbix.feature.home.MainActivity;
import com.pubbix.feature.onboarding.OnboardingActivity;
import com.pubbix.feature.onboarding.OnboardingActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
        OnboardingActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivtyInjector(MainActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(OnboardingActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideOnboardingActivityInjector(OnboardingActivityComponent.Builder builder);
}

