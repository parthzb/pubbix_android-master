package com.pubbix.di;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.pubbix.base.BaseActivity;
import com.pubbix.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {

    private final Map<Class<? extends Fragment>, Provider<AndroidInjector.Factory<? extends Fragment>>> screenInjectors;
    private final Map<String, AndroidInjector<Fragment>> cache = new HashMap<>();

    @Inject
    ScreenInjector(Map<Class<? extends Fragment>, Provider<AndroidInjector.Factory<? extends Fragment>>> screenInjectors) {
        this.screenInjectors = screenInjectors;
    }

    void inject(Fragment fragment) {
        if (!(fragment instanceof BaseFragment)) {
            throw new IllegalArgumentException("Fragment must extend BaseFragment");
        }

        String instanceId = fragment.getArguments().getString("instance_id");
        if (cache.containsKey(instanceId)) {
            cache.get(instanceId).inject(fragment);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Fragment> injectorFactory =
                (AndroidInjector.Factory<Fragment>) screenInjectors.get(fragment.getClass()).get();
        AndroidInjector<Fragment> injector = injectorFactory.create(fragment);
        cache.put(instanceId, injector);
        injector.inject(fragment);
    }

    void clear(Fragment fragment) {
        AndroidInjector<?> injector = cache.remove(fragment.getArguments().getString("instance_id"));
        if (injector instanceof ScreenComponent) {
            ((ScreenComponent) injector).disposableManager().dispose();
        }
    }

    static ScreenInjector get(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Fragment must be hosted by BaseActivity");
        }
        return ((BaseActivity) activity).getScreenInjector();
    }
}
