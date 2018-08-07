package com.pubbix.feature.home.listingdetail;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        ListingDetailScreenModule.class,
})
public interface ListingDetailComponent extends ScreenComponent<ListingDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ListingDetailFragment> {

    }
}
