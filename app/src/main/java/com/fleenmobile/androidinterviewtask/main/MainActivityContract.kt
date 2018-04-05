package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.Recipe

interface MainActivityContract {

    interface View {
        fun showRecipes(recipes: List<Recipe>)
        fun showProgress()
        fun hideProgress()
        fun showIngredients(ingredients: List<Ingredient>)
        fun showError(errorMessage: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun onSearchTerm(term: String)
        fun onIngredientsSelected(ingredients: List<Ingredient>)
    }
}