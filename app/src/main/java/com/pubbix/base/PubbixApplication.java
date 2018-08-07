package com.pubbix.base;

import android.app.Application;

import com.pubbix.BuildConfig;
import com.pubbix.di.ActivityInjector;
import com.pubbix.modules.AndroidModule;
import com.pubbix.modules.ApiModule;
import com.pubbix.modules.NetModule;
import com.pubbix.modules.RetrofitModule;

import javax.inject.Inject;

import timber.log.Timber;

public class PubbixApplication extends Application {

    @Inject
    ActivityInjector mActivityInjector;

    protected ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = initComponent();
        mComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .netModule(new NetModule())
                .retrofitModule(new RetrofitModule())
                .apiModule(new ApiModule())
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return mActivityInjector;
    }
}
