package com.fleenmobile.androidinterviewtask.main.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import com.fleenmobile.androidinterviewtask.BaseActivity
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.Recipe
import com.fleenmobile.androidinterviewtask.hide
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.adapter.RecipesAdapter
import com.fleenmobile.androidinterviewtask.show
import com.fleenmobile.androidinterviewtask.showToast
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityContract.Presenter>(),
        MainActivityContract.View {

    @BindView(R.id.recipe_list)
    lateinit var recipesRecyclerView: RecyclerView

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var recipesAdapter: RecipesAdapter

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeRecipesList()
    }

    private fun initializeRecipesList() {
        recipesRecyclerView.layoutManager = layoutManager
        recipesAdapter.onIngredientsShowListener = { presenter.onIngredientsSelected(it) }
        recipesRecyclerView.adapter = recipesAdapter
    }

    //region View
    override fun showRecipes(recipes: List<Recipe>) {
        recipesAdapter.refreshData(recipes)
    }

    override fun showProgress() {
        recipesRecyclerView.hide()
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
        recipesRecyclerView.show()
    }

    override fun showIngredients(ingredients: List<Ingredient>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMessage: String) {
        showToast(errorMessage, Toast.LENGTH_LONG)
    }
    //endregion
}
