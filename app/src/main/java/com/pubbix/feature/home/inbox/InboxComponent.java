package com.pubbix.feature.home.inbox;

import com.pubbix.base.ScreenModule;
import com.pubbix.di.ScreenComponent;
import com.pubbix.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
})
public interface InboxComponent extends ScreenComponent<InboxFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<InboxFragment> {

    }
}
