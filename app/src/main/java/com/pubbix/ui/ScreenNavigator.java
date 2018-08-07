package com.pubbix.ui;

import android.support.v4.app.Fragment;

import com.pubbix.base.BaseFragment;

public interface ScreenNavigator {

    void changeScreen(BaseFragment fragment);

    void changeScreen(Fragment fragment);

    boolean pop();
}
