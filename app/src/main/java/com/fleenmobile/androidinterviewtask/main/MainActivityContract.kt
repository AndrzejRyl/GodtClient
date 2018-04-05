package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract

interface MainActivityContract {

    interface View {
        fun showRecipes()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSearchTerm(term: String)
    }
}