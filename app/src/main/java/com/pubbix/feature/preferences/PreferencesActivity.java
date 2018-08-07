package com.pubbix.feature.preferences;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pubbix.R;
import com.pubbix.feature.preferences.fragment.CategoriesPrefsFragment;
import com.pubbix.feature.preferences.fragment.NotificationPrefsFragment;

public class PreferencesActivity extends AppCompatActivity {
    public static final String NOTIFICATION_FRAGMENT = "notification_prefs";
    public static final String CATEGORIES_FRAGMENT = "categories_prefs";
    public static final String FRAGMENT_KEY = "fragmentKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Toolbar toolbar = findViewById(R.id.preferences_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            PreferenceFragmentCompat fragment = null;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String fragmentKey = extras.getString(FRAGMENT_KEY);
                assert fragmentKey != null;
                switch (fragmentKey) {
                    case NOTIFICATION_FRAGMENT:
                        fragment = new NotificationPrefsFragment();
                        actionBar.setTitle("Manage your notifications");
                        break;

                    case CATEGORIES_FRAGMENT:
                        fragment = new CategoriesPrefsFragment();
                        actionBar.setTitle("Manage your preferences");
                        break;
                    default:
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.preference_root, fragment).commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
