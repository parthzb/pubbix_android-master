package com.pubbix.modules

import com.pubbix.data.remote.PubbixApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule{
    @Provides
    @Singleton
    fun providesPubbixApi(retrofit: Retrofit): PubbixApi {
        return retrofit.create(PubbixApi::class.java)
    }
}