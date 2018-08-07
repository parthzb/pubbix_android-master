package com.pubbix.feature.onboarding;

import android.support.v4.app.Fragment;

import com.pubbix.R;
import com.pubbix.base.BaseActivity;
import com.pubbix.feature.onboarding.login.LoginFragment;

public class OnboardingActivity extends BaseActivity {
    @Override
    protected int layoutRes() {
        return R.layout.activity_onboarding;
    }

    @Override
    public Fragment initialScreen() {
        return LoginFragment.newInstance();
    }
}
