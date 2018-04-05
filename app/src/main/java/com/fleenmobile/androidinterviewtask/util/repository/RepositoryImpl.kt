package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.api.ApiService
import com.fleenmobile.androidinterviewtask.data.Recipe
import io.reactivex.Observable

class RepositoryImpl(private val apiService: ApiService) : Repository {

    override fun recipes(): Observable<List<Recipe>> = apiService.recipes()

    override fun filteredRecipes(searchTerm: String): Observable<List<Recipe>> =
            recipes()
                    .flatMapIterable { it }
                    .filter {
                        val matchesTitle = it.title.toLowerCase().contains(searchTerm.toLowerCase())
                        val matchesIngredients =
                                it.ingredients
                                        .map { it.elements }
                                        .map { it.joinToString() }
                                        .filter { it.toLowerCase().contains(searchTerm.toLowerCase()) }
                                        .count() > 0

                        return@filter matchesTitle || matchesIngredients
                    }
                    .toList()
                    .toObservable()
}