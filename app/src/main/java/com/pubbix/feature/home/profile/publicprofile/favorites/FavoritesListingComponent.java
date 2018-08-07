package com.pubbix.feature.home.profile.publicprofile.favorites;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        FavoritesListingScreenModule.class,
})
public interface FavoritesListingComponent extends ScreenComponent<FavoritesListingFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<FavoritesListingFragment> {

    }
}
