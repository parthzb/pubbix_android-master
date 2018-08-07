package com.pubbix.modules

import com.google.firebase.messaging.FirebaseMessaging
import com.pubbix.data.DataManager
import com.pubbix.util.NotificationHelper
import com.pubbix.util.UserHelper
import com.pubbix.util.rx.RxEventBus
import com.pubbix.util.rx.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelperModule {
    @Provides
    @Singleton
    fun providesScheduler(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @Singleton
    fun providesUserHelper(dataManager: DataManager): UserHelper {
        return UserHelper(dataManager)
    }

    @Provides
    @Singleton
    fun providesFirebaseMessagingInstance(): FirebaseMessaging {
        return FirebaseMessaging.getInstance()
    }

    @Provides
    @Singleton
    fun providesNotificationHelper(firebaseMessaging: FirebaseMessaging): NotificationHelper {
        return NotificationHelper(firebaseMessaging)
    }

    @Provides
    @Singleton
    fun providesRxEventBus(): RxEventBus {
        return RxEventBus.getInstance()
    }

}