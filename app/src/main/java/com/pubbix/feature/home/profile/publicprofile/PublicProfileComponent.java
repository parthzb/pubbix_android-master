package com.pubbix.feature.home.profile.publicprofile;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        PublicProfileScreenModule.class,
})
public interface PublicProfileComponent extends ScreenComponent<PublicProfileFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PublicProfileFragment> {

    }
}
