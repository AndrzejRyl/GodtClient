package com.fleenmobile.androidinterviewtask.data

data class Recipe(
        val id: Long,
        val title: String,
        val description: String,
        val ingredients: List<Ingredient>,
        val images: List<RecipeImage>
) {
    fun photoUrl() = if (images.size > 0) images[0].url else null
}