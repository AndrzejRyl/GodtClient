package com.fleenmobile.androidinterviewtask.util.injection.module

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.adapter.RecipesAdapter
import com.fleenmobile.androidinterviewtask.main.presentation.MainActivityPresenter
import com.fleenmobile.androidinterviewtask.main.ui.MainActivity
import com.fleenmobile.androidinterviewtask.util.injection.RuntimeScope
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View = activity

    @Provides
    @RuntimeScope
    fun presenter(
            view: MainActivityContract.View,
            repository: Repository,
            compositeDisposable: CompositeDisposable
    ): MainActivityContract.Presenter = MainActivityPresenter(view, repository, compositeDisposable)

    @Provides
    fun adapter(): RecipesAdapter =
            RecipesAdapter(arrayListOf())

    @Provides
    fun layoutManager(context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context)
}