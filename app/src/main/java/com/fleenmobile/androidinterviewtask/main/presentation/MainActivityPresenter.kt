package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityPresenter(
        private val view: MainActivityContract.View,
        private val repository: Repository,
        private val compositeDisposable: CompositeDisposable
) : MainActivityContract.Presenter {

    override fun initialize() {
        compositeDisposable.add(
                repository
                        .recipes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { view.showProgress() }
                        .doOnTerminate { view.hideProgress() }
                        .subscribe(
                                { view.showRecipes(it) },
                                {
                                    Timber.e(it)
                                    view.showError("There was a problem with connection. Try again.")
                                }
                        )
        )
    }

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun onSearchTerm(term: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onIngredientsSelected(ingredients: List<Ingredient>) {
        view.showIngredients(ingredients)
    }
}