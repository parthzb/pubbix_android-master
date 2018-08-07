package com.pubbix.data.local;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pubbix.data.model.Category;
import com.pubbix.data.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by AugusteC on 3/26/17.
 */
@Singleton
public class PreferencesHelper {
    public static final String PREF_FILE_NAME = "pubbix_app_pref_file";

    private static final String PREF_KEY_USER = "PREF_KEY_USER";
    private static final String PREF_KEY_CATEGORY = "PREF_KEY_CATEGORY";
    private static final String PREF_KEY_LOGIN_STATUS = "PREF_KEY_LOGIN_STATUS";
    private static final String PREF_KEY_FIREBASE_ID = "PREF_KEY_FIREBASE_ID";
    private static final String PREF_KEY_FIRST_RUN = "PREF_KEY_FIRST_RUN";

    private final SharedPreferences mPref;
    private final Gson mGson;

    @Inject
    public PreferencesHelper(SharedPreferences preferences,
                             Gson gson) {
        mPref = preferences;
        mGson = gson;
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putUser(User user) {
        mPref.edit().putString(PREF_KEY_USER, mGson.toJson(user)).apply();
    }

    @Nullable
    public User getUser() {
        String userJson = mPref.getString(PREF_KEY_USER, null);
        if (userJson != null) {
            return mGson.fromJson(userJson, new TypeToken<User>() {
            }.getType());
        }

        return null;
    }

    public void putCategories(List<Category> categories) {
        mPref.edit().putString(PREF_KEY_CATEGORY, mGson.toJson(categories)).apply();
    }

    @Nullable
    public List<Category> getCategories() {
        String categoryJson = mPref.getString(PREF_KEY_CATEGORY, null);
        if (categoryJson != null) {
            return mGson.fromJson(categoryJson, new TypeToken<List<Category>>() {
            }.getType());
        }

        return null;
    }

    public void putFirebaseId(final String firebaseId) {
        Timber.d("Firebase Id: " + firebaseId);
        mPref.edit().putString(PREF_KEY_FIREBASE_ID, firebaseId).apply();
    }

    @Nullable
    public String getFirebaseId() {
        return mPref.getString(PREF_KEY_FIREBASE_ID, null);
    }

    public void putLoginStatus(final boolean isLogged) {
        mPref.edit().putBoolean(PREF_KEY_LOGIN_STATUS, isLogged).apply();
    }

    public boolean isLoggedIn() {
        return mPref.getBoolean(PREF_KEY_LOGIN_STATUS, false);
    }

    public void putFirstRun(final boolean isLogged) {
        mPref.edit().putBoolean(PREF_KEY_FIRST_RUN, isLogged).apply();
    }

    public boolean isFirstRun() {
        return mPref.getBoolean(PREF_KEY_FIRST_RUN, true);
    }
}
