package com.pubbix.feature.preferences.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.pubbix.R;
import com.pubbix.util.NotificationHelper;

import java.util.UUID;

import javax.inject.Inject;

public class NotificationPrefsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Inject
    NotificationHelper notificationHelper;

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new NotificationPrefsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.notification_preferences, rootKey);
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        /*notificationHelper.manageSubscriptions(key, sharedPreferences.getBoolean(key, false));
        Timber.d("Preference value is: " + sharedPreferences.getBoolean(key, false));*/
    }
}

