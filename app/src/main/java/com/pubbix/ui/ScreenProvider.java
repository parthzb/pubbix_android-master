package com.pubbix.ui;

import android.support.v4.app.Fragment;

/**
 * To be implemented by all Activities. This is used in {@link ScreenNavigator} implementations.
 */
public interface ScreenProvider {

    Fragment initialScreen();
}
