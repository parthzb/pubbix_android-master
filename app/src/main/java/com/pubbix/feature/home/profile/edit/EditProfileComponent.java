package com.pubbix.feature.home.profile.edit;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        EditProfileScreenModule.class,
})
public interface EditProfileComponent extends ScreenComponent<EditProfileFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<EditProfileFragment> {

    }
}
