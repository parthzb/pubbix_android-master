package com.pubbix.base;

import com.pubbix.di.ForScreen;
import com.pubbix.di.ScreenScope;
import com.pubbix.lifecycle.DisposableManager;
import com.pubbix.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
