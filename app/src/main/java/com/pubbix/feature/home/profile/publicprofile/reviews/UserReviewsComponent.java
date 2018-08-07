package com.pubbix.feature.home.profile.publicprofile.reviews;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        UserReviewsScreenModule.class,
})
public interface UserReviewsComponent extends ScreenComponent<UserReviewsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserReviewsFragment> {

    }
}
