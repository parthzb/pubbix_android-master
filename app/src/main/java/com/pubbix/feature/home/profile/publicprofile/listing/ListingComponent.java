package com.pubbix.feature.home.profile.publicprofile.listing;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        ListingScreenModule.class,
})
public interface ListingComponent extends ScreenComponent<ListingFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ListingFragment> {

    }
}
