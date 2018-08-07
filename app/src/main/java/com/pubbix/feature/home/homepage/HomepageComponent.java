package com.pubbix.feature.home.homepage;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        HomepageScreenModule.class,
})
public interface HomepageComponent extends ScreenComponent<HomepageFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomepageFragment> {

    }
}
