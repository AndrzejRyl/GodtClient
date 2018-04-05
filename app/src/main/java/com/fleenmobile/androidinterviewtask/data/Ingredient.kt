package com.fleenmobile.androidinterviewtask.data

data class IngredientElement(
        val name: String,
        private val amount: Double,
        private val symbol: String) {
    fun amountWithUnits() = "$amount $symbol"
}

data class Ingredient(
        val elements: List<IngredientElement>
)