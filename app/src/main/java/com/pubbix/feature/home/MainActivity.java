package com.pubbix.feature.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.pubbix.R;
import com.pubbix.base.BaseActivity;
import com.pubbix.base.BaseFragment;
import com.pubbix.data.DataManager;
import com.pubbix.data.event.SnackbarEvent;
import com.pubbix.feature.home.homepage.HomepageFragment;
import com.pubbix.feature.home.inbox.InboxFragment;
import com.pubbix.feature.home.profile.ProfileFragment;
import com.pubbix.ui.DefaultScreenNavigator;
import com.pubbix.util.NotificationHelper;
import com.pubbix.util.rx.RxEventBus;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    DefaultScreenNavigator navigator;
    @Inject
    DataManager dataManager;
    @Inject
    NotificationHelper notificationHelper;
    @Inject
    RxEventBus eventBus;

    private CoordinatorLayout rootLayout;
    private AHBottomNavigation navigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        super.onCreate(savedInstanceState);
        rootLayout = findViewById(R.id.root_layout);

        setBottomNavigationBar();

        if (dataManager.getPreferencesHelper().getFirebaseId() != null &&
                dataManager.getPreferencesHelper().isFirstRun()) {
            notificationHelper.subscribeToAllChannels();
            dataManager.getPreferencesHelper().putFirstRun(false);
        }

        //Managing events
        disposable.add(eventBus.getEvents().subscribe(o -> {
            if (o instanceof SnackbarEvent) {
                showSnackBar((SnackbarEvent) o);
            }
        }));
    }

    private void setBottomNavigationBar() {
        navigation = findViewById(R.id.navigationView);

        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation);
        navigationAdapter.setupWithBottomNavigation(navigation, tabColors);
        //navigation.setAccentColor(R.color.pubbixMainColor);
        navigation.setAccentColor(ContextCompat.getColor(this, R.color.pubbixMainColor));

        navigation.setOnTabSelectedListener((position, wasSelected) -> {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = HomepageFragment.newInstance();
                    break;

                /*case R.id.favorites:
                    fragment = FavoriteFragment.newInstance();
                    break;*/

                case 1:
                    fragment = InboxFragment.newInstance();
                    break;

                case 2:
                    fragment = ProfileFragment.newInstance();
                    break;
            }

            return loadFragment(fragment);
        });
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Fragment initialScreen() {
        return HomepageFragment.newInstance();
    }

    private void showSnackBar(SnackbarEvent event) {
        Snackbar snackbar = Snackbar.make(rootLayout, event.message, Snackbar.LENGTH_LONG);
        snackbar.setDuration(Snackbar.LENGTH_LONG);
        ViewGroup group = (ViewGroup) snackbar.getView();
        TextView actionText = group.findViewById(R.id.snackbar_action);
        switch (event.type) {
            case INFO:
                actionText.setTextColor(Color.WHITE);
                break;
            case WARNING:
                actionText.setTextColor(Color.YELLOW);
                break;
            case ERROR:
                actionText.setTextColor(Color.RED);
                break;
            default:
                actionText.setTextColor(Color.WHITE);
                break;
        }
        //group.setBackgroundColor(ContextCompat.getColor(this, R.color.red_800));
        TextView msgText = group.findViewById(R.id.snackbar_text);
        msgText.setMaxLines(5);
        snackbar.show();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            navigator.changeScreen((BaseFragment) fragment);
            return true;
        }
        return false;
    }

    public void setBottomNavigationVisibility(int visibility) {
        navigation.setVisibility(visibility);
    }
}
