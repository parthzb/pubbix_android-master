package com.pubbix.feature.home;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ActivityScope;
import com.pubbix.ui.ActivityViewInterceptorModule;
import com.pubbix.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
        ScreenModule.class,
        ActivityViewInterceptorModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }
}
