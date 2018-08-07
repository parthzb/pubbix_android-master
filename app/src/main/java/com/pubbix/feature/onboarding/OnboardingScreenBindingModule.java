package com.pubbix.feature.onboarding;


import android.support.v4.app.Fragment;

import com.pubbix.feature.onboarding.login.LoginComponent;
import com.pubbix.feature.onboarding.login.LoginFragment;
import com.pubbix.feature.onboarding.registration.RegistrationComponent;
import com.pubbix.feature.onboarding.registration.RegistrationFragment;
import com.pubbix.util.rx.scheduler.SchedulerProvider;
import com.pubbix.util.rx.scheduler.SchedulerProviderContract;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module(subcomponents = {
        LoginComponent.class,
        RegistrationComponent.class
})
public abstract class OnboardingScreenBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindLoginInjector(LoginComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RegistrationFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRegistrationInjector(RegistrationComponent.Builder builder);

    @Binds
    abstract CompositeDisposable provideCompositeDisposable(CompositeDisposable compositeDisposable);

    @Binds
    abstract SchedulerProviderContract provideSchedulerProvider(SchedulerProvider scheduler);
}
