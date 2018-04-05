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
        downloadRecipesList()
        setupSearchBar()
    }

    private fun setupSearchBar() {
        compositeDisposable.add(
                view
                        .getSearchTextWatcher()
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { view.showProgress() }
                        .observeOn(Schedulers.io())
                        .switchMap { repository.filteredRecipes(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    view.showRecipes(it)
                                    view.hideProgress()
                                },
                                {
                                    Timber.e(it)
                                    view.showError("There was a problem with connection. Try again.")
                                    view.hideProgress()
                                }
                        )
        )
    }

    private fun downloadRecipesList() {
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

    override fun onIngredientsSelected(ingredients: List<Ingredient>) {
        view.showIngredients(ingredients)
    }
}