package com.pubbix.feature.home.profile.about;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        AboutUsScreenModule.class
})
public interface AboutUsComponent extends ScreenComponent<AboutUsFragment>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AboutUsFragment> {

    }
}
