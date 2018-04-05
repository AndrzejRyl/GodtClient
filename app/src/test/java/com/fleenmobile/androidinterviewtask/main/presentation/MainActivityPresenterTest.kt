package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.BaseTest
import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.Recipe
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class MainActivityPresenterTest : BaseTest() {

    @Mock
    lateinit var view: MainActivityContract.View

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var recipe: Recipe

    private val mockString = "mock"
    private lateinit var mockRecipes: List<Recipe>
    private lateinit var presenter: MainActivityContract.Presenter

    override fun setup() {
        super.setup()

        trampolineRxPlugin()
        mockRecipes = arrayListOf(recipe)

        presenter = MainActivityPresenter(
                view,
                repository,
                compositeDisposable
        )
    }

    override fun tearDown() {
        super.tearDown()
        Mockito.verifyNoMoreInteractions(view, compositeDisposable, repository, recipe)
    }

    @Test
    fun `should download recipes and setup search bar on startup`() {
        mockTextWatcherAnswer()
        mockPositiveRecipesAnswer()
        mockPositiveFilteredRecipesAnswer()

        presenter.initialize()

        commonInitializeVerification()

        verify(repository, times(1)).filteredRecipes(mockString)
        verify(view, times(2)).showRecipes(mockRecipes)
    }

    private fun commonInitializeVerification() {
        verify(compositeDisposable, times(2)).add(any())
        verify(view, times(1)).getSearchTextWatcher()
        verify(repository, times(1)).recipes()
        verify(view, times(2)).showProgress()
        verify(view, times(2)).hideProgress()
    }

    private fun mockPositiveFilteredRecipesAnswer() {
        `when`(repository.filteredRecipes(mockString)).thenReturn(Observable.just(mockRecipes))
    }

    private fun mockPositiveRecipesAnswer() {
        `when`(repository.recipes()).thenReturn(Observable.just(mockRecipes))
    }

    private fun mockTextWatcherAnswer() {
        `when`(view.getSearchTextWatcher()).thenReturn(Observable.just(mockString))
    }

    @Test
    fun `should show error in case filtered query fails`() {
        mockTextWatcherAnswer()
        mockPositiveRecipesAnswer()
        mockNegativeFilteredRecipesAnswer()

        presenter.initialize()

        commonInitializeVerification()
        verify(view, times(1)).showError(anyString())
        verify(view, times(1)).showRecipes(mockRecipes)
        verify(repository, times(1)).filteredRecipes(mockString)
    }

    private fun mockNegativeFilteredRecipesAnswer() {
        `when`(repository.filteredRecipes(mockString)).thenThrow(IllegalStateException())
    }

    @Test
    fun `should show error in case initial query fails`() {
        mockTextWatcherAnswer()
        //todo mockNegativeRecipesAnswer()
        mockPositiveFilteredRecipesAnswer()

        //todo
//        presenter.initialize()
    }

    @Test
    fun `should clean up on clear`() {
        presenter.clear()

        verify(compositeDisposable, times(1)).clear()
    }

    @Test
    fun `should show ingredients on clicking on button`() {
        val list = arrayListOf<Ingredient>()

        presenter.onIngredientsSelected(list)

        verify(view, times(1)).showIngredients(list)
    }
}