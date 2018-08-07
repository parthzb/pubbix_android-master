package com.pubbix.data;

import com.pubbix.data.local.PreferencesHelper;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by AugusteC on 4/23/17.
 */

@Singleton
public class DataManager {
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper) {
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    /*public Observable<Listings> getLatestListings() {
        return mPubbixApi.getLatestListings();
    }

    public Observable<List<PopularKeywords>> getPopularKeywords() {
        return mPubbixApi.getPopularKeywords();
    }

    public Observable<List<Category>> getCategories() {
        return mPubbixApi.getCategories();
    }

    public Observable<Listings> getUserLatestListings(final String userId) {
        return mPubbixApi.getUserLatestListings(userId);
    }

    public Observable<Profile> registerUser(final Credentials credentials) {
        return mPubbixApi.createUserAccount(credentials);
    }

    public Observable<Profile> updateUserProfile(final User user) {
        return mPubbixApi.updateUserProfile(user);
    }

    public Observable<Profile> getUserDetails(final String userId) {
        return mPubbixApi.getUserDetails(userId);
    }

    public Observable<Status> publishListing(final Map<String, RequestBody> listing, final List<MultipartBody.Part> pictures) {
        return mPubbixApi.publishListing(listing, pictures);
    }*/

}
