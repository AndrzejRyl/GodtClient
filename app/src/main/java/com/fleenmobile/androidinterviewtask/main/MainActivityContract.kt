package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Recipe

interface MainActivityContract {

    interface View {
        fun showRecipes(recipes: List<Recipe>)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSearchTerm(term: String)
    }
}