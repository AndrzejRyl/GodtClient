package com.fleenmobile.androidinterviewtask.data

data class Recipe(
        val id: Long,
        val title: String,
        val description: String,
        val ingredients: List<Ingredient>
)