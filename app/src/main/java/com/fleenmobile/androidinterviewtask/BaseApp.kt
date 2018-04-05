package com.fleenmobile.androidinterviewtask

import android.app.Activity
import android.app.Application
import com.fleenmobile.androidinterviewtask.util.injection.DaggerAppComponent
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

    private fun initAppComponent() =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()
                    .inject(this)

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
