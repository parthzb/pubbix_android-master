package com.pubbix.feature.home.profile.edit.generic;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        GenericProfileEditScreenModule.class,
})
public interface GenericProfileEditComponent extends ScreenComponent<GenericProfileEditFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GenericProfileEditFragment> {

    }
}
