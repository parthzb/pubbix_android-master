package com.pubbix.feature.home.profile;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        ProfileScreenModule.class,
})
public interface ProfileComponent extends ScreenComponent<ProfileFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProfileFragment> {

    }
}
