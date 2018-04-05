package com.fleenmobile.androidinterviewtask.main.ui

import com.fleenmobile.androidinterviewtask.BaseActivity
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Recipe
import com.fleenmobile.androidinterviewtask.main.MainActivityContract

class MainActivity : BaseActivity<MainActivityContract.Presenter>(),
        MainActivityContract.View {

    override val layoutId: Int = R.layout.activity_main

    //region View
    override fun showRecipes(recipes: List<Recipe>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}
