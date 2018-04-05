package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.IngredientElement
import com.fleenmobile.androidinterviewtask.data.Recipe
import io.reactivex.Observable

class RepositoryImpl : Repository {

    override fun recipes(): Observable<List<Recipe>> {
        // todo : Replace with real implementation

        return Observable.just(
                listOf(
                        Recipe(1, "Recipe1", "Desc1", arrayListOf()),
                        Recipe(2, "Recipe2", "Desc2", arrayListOf(
                                Ingredient(listOf(IngredientElement("Salt", 12, "g"))),
                                Ingredient(listOf(IngredientElement("Sugar", 123, "g")))
                        ))
                )
        )
    }
}