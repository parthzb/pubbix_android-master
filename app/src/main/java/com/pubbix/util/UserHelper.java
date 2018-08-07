package com.pubbix.util;

import com.pubbix.data.DataManager;
import com.pubbix.data.model.User;
import com.pubbix.di.ApplicationContext;

import javax.inject.Inject;

@ApplicationContext
public class UserHelper {
    private DataManager dataManager;

    @Inject
    public UserHelper(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public User getUser() {
        return dataManager.getPreferencesHelper().getUser();
    }

    public void saveUser(User user) {
        dataManager.getPreferencesHelper().putUser(user);
    }

    public boolean isLoggedIn() {
        return dataManager.getPreferencesHelper().isLoggedIn();
    }

    public void logUserIn() {
        dataManager.getPreferencesHelper().putLoginStatus(true);
    }

    public void logUserOff() {
        saveUser(null);
        dataManager.getPreferencesHelper().putLoginStatus(false);
    }
}
