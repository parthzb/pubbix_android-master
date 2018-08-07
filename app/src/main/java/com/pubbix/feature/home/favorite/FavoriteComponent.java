package com.pubbix.feature.home.favorite;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
})
public interface FavoriteComponent extends ScreenComponent<FavoriteFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<FavoriteFragment> {

    }
}
