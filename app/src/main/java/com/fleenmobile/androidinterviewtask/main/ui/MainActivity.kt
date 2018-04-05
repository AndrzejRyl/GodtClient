package com.fleenmobile.androidinterviewtask.main.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import com.fleenmobile.androidinterviewtask.BaseActivity
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.Recipe
import com.fleenmobile.androidinterviewtask.hide
import com.fleenmobile.androidinterviewtask.hideKeyboard
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.adapter.RecipesAdapter
import com.fleenmobile.androidinterviewtask.show
import com.fleenmobile.androidinterviewtask.showToast
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityContract.Presenter>(),
        MainActivityContract.View {

    companion object {
        const val EDIT_TEXT_TIMEOUT = 600L
    }

    @BindView(R.id.recipe_list)
    lateinit var recipesRecyclerView: RecyclerView

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.search_bar)
    lateinit var searchEditText: EditText

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
        hideKeyboard()
    }

    override fun hideProgress() {
        progressBar.hide()
        recipesRecyclerView.show()
    }

    override fun showIngredients(ingredients: List<Ingredient>) {
        showToast("not implemented", Toast.LENGTH_LONG)
    }

    override fun showError(errorMessage: String) {
        showToast(errorMessage, Toast.LENGTH_LONG)
    }

    override fun getSearchTextWatcher(): Observable<String> =
            RxTextView
                    .textChanges(searchEditText)
                    .map { it.toString() }
                    .debounce(EDIT_TEXT_TIMEOUT, TimeUnit.MILLISECONDS)
    //endregion
}
