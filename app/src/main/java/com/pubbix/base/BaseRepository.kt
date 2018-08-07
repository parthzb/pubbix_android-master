package com.pubbix.base

import com.pubbix.data.remote.PubbixApi
import com.pubbix.util.UserHelper
import com.pubbix.util.rx.scheduler.SchedulerProvider
import javax.inject.Inject

open class BaseRepository<I : Any> {
    @Inject
    protected lateinit var pubbixApi: PubbixApi

    @Inject
    protected lateinit var schedulerProvider: SchedulerProvider

    @Inject
    protected lateinit var userHelper: UserHelper
}