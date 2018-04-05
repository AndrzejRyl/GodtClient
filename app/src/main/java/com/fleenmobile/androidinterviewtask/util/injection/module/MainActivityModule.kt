package com.fleenmobile.androidinterviewtask.util.injection.module

import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.presentation.MainActivityPresenter
import com.fleenmobile.androidinterviewtask.main.ui.MainActivity
import com.fleenmobile.androidinterviewtask.util.injection.RuntimeScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View = activity

    @Provides
    @RuntimeScope
    fun presenter(view: MainActivityContract.View): MainActivityContract.Presenter =
            MainActivityPresenter(view)
}