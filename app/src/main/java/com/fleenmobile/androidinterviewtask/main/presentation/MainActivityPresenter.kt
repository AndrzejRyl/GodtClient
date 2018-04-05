package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.util.repository.Repository

class MainActivityPresenter(
        private val view: MainActivityContract.View,
        private val repository: Repository
) : MainActivityContract.Presenter {

    override fun initialize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchTerm(term: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}