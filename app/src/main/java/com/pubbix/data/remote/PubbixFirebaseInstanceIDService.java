package com.pubbix.data.remote;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.pubbix.data.DataManager;

import javax.inject.Inject;

import timber.log.Timber;


public class PubbixFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = PubbixFirebaseInstanceIDService.class.getSimpleName();
    @Inject
    DataManager dataManager;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        /*if (MobiComUserPreference.getInstance(this).isRegistered()) {
            try {
                new RegisterUserClientService(this).updatePushNotificationId(refreshedToken);
            } catch (Exception e) {
                Timber.e(e);
            }
        }*/
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Timber.d(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        dataManager.getPreferencesHelper().putFirebaseId(token);
    }
}
