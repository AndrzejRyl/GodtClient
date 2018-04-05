package com.fleenmobile.androidinterviewtask.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Ingredient
import com.fleenmobile.androidinterviewtask.data.Recipe

typealias OnIngredientsShowListener = (ingredients: List<Ingredient>) -> Unit

class RecipesAdapter(private val data: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeViewHolder>() {

    var onIngredientsShowListener: OnIngredientsShowListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_item_view, parent, false)
        return RecipeViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = data[position]

        holder.apply {
            setRecipe(recipe)
            onIngredientsClickedListener = { onIngredientsShowListener?.invoke(recipe.ingredients) }
        }
    }

    fun refreshData(recipes: List<Recipe>) {
        data.clear()
        data.addAll(recipes)
        notifyDataSetChanged()
    }
}