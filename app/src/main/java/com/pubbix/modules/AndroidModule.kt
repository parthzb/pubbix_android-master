package com.pubbix.modules

import android.app.Application
import android.content.Context
import com.pubbix.data.DataManager
import com.pubbix.data.local.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }

    /*@Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    }*/

    @Provides
    @Singleton
    fun providesDataManager(preferencesHelper: PreferencesHelper): DataManager{
        return DataManager(preferencesHelper)
    }
}