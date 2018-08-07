package com.pubbix.util;

import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotificationHelper {
    public static final String CHANNEL_NEW_LISTING = "newListing";
    public static final String CHANNEL_EXPIRING_LISTING = "expiringListing";
    public static final String CHANNEL_EXPIRED_LISTING = "expiredListing";
    public static final String CHANNEL_CHAT_MESSAGES = "chatMessages";
    public static final String CHANNEL_PROMOTIONS = "promotions";
    public static final String CHANNEL_DEAL_OF_DAY = "dealOfTheDay";

    public static final String CATEGORY_DISCOVER = "discover";
    public static final String CATEGORY_PROPERTY= "property";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_FOR_SALE = "forSale";

    private FirebaseMessaging firebaseMessaging;

    @Inject
    public NotificationHelper(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void subscribeToTopic(String topic) {
        firebaseMessaging.subscribeToTopic(topic);
    }

    public void unsubscribeFromTopic(String topic) {
        firebaseMessaging.unsubscribeFromTopic(topic);
    }

    public void subscribeToAllChannels() {
        firebaseMessaging.subscribeToTopic(CHANNEL_NEW_LISTING);
        firebaseMessaging.subscribeToTopic(CHANNEL_EXPIRING_LISTING);
        firebaseMessaging.subscribeToTopic(CHANNEL_EXPIRED_LISTING);
        firebaseMessaging.subscribeToTopic(CHANNEL_CHAT_MESSAGES);
        firebaseMessaging.subscribeToTopic(CHANNEL_PROMOTIONS);
        firebaseMessaging.subscribeToTopic(CHANNEL_DEAL_OF_DAY);
    }

    public void unsubscribeFromAllChannels() {
        firebaseMessaging.unsubscribeFromTopic(CHANNEL_NEW_LISTING);
        firebaseMessaging.unsubscribeFromTopic(CHANNEL_EXPIRING_LISTING);
        firebaseMessaging.unsubscribeFromTopic(CHANNEL_EXPIRED_LISTING);
        firebaseMessaging.unsubscribeFromTopic(CHANNEL_CHAT_MESSAGES);
        firebaseMessaging.unsubscribeFromTopic(CHANNEL_PROMOTIONS);
        firebaseMessaging.unsubscribeFromTopic(CHANNEL_DEAL_OF_DAY);
    }

    public void subscribeToAllCategories() {
        firebaseMessaging.subscribeToTopic(CATEGORY_DISCOVER);
        firebaseMessaging.subscribeToTopic(CATEGORY_PROPERTY);
        firebaseMessaging.subscribeToTopic(CATEGORY_SERVICE);
        firebaseMessaging.subscribeToTopic(CATEGORY_FOR_SALE);
    }

    public void unsubscribeFromAllCategories() {
        firebaseMessaging.unsubscribeFromTopic(CATEGORY_DISCOVER);
        firebaseMessaging.unsubscribeFromTopic(CATEGORY_PROPERTY);
        firebaseMessaging.unsubscribeFromTopic(CATEGORY_SERVICE);
        firebaseMessaging.unsubscribeFromTopic(CATEGORY_FOR_SALE);
    }

    public void manageSubscriptions(String channel, boolean joinChannel){
        if(joinChannel){
            firebaseMessaging.subscribeToTopic(channel);
        }else {
            firebaseMessaging.unsubscribeFromTopic(channel);
        }
    }

}
