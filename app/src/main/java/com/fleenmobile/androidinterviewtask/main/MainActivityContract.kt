package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.Recipe
import io.reactivex.Observable

interface MainActivityContract {

    interface View {
        fun showRecipes(recipes: List<Recipe>)
        fun showProgress()
        fun hideProgress()
        fun showIngredients(ingredients: List<Ingredient>)
        fun showError(errorMessage: String)
        fun getSearchTextWatcher(): Observable<String>
    }

    interface Presenter : BaseContract.Presenter {
        fun onIngredientsSelected(ingredients: List<Ingredient>)
    }
}