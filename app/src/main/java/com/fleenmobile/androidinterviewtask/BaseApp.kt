package com.fleenmobile.androidinterviewtask

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class BaseApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        init()
    }

    override fun activityInjector(): AndroidInjector<Activity> =
            activityDispatchingAndroidInjector

    open fun init() {
        initTimber()
        initAppComponent()
    }

    private fun initAppComponent() {
        //todo
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
