package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.IngredientElement
import com.fleenmobile.androidinterviewtask.data.Recipe
import com.fleenmobile.androidinterviewtask.data.RecipeImage
import io.reactivex.Observable

class RepositoryImpl : Repository {

    override fun recipes(): Observable<List<Recipe>> {
        // todo : Replace with real implementation

        return Observable.just(
                listOf(
                        Recipe(1, "Recipe1", "Desc1", arrayListOf(), arrayListOf()),
                        Recipe(2, "Recipe2", "Desc2", arrayListOf(
                                Ingredient(listOf(IngredientElement("Salt", 12, "g"))),
                                Ingredient(listOf(IngredientElement("Sugar", 123, "g")))
                        ),
                                arrayListOf(RecipeImage("https://imbo.vgc.no/users/godt/images/259b146ff8d7319d8d2206d19c1b65f8.jpg?t%5B0%5D=thumbnail%3Awidth%3D490%2Cheight%3D277%2Cfit%3Doutbound&t%5B1%5D=strip&t%5B2%5D=compress%3Alevel%3D75&t%5B3%5D=progressive&accessToken=d252cb87cd4e11283eecea7eba4e5f8e9fdb81293fc8a726a5129a79af837020")))
                )
        )
    }
}