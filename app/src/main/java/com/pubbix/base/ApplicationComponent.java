package com.pubbix.base;

import com.pubbix.modules.AndroidModule;
import com.pubbix.modules.ApiModule;
import com.pubbix.modules.HelperModule;
import com.pubbix.modules.NetModule;
import com.pubbix.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApiModule.class,
        NetModule.class,
        HelperModule.class,
        RetrofitModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {
    void inject(PubbixApplication pubbixApplication);
}
