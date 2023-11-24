package com.example.crispify.fake

import com.example.crispify.Recipe
import com.example.crispify.data.RecipesRepository

class FakeNetworkRecipesRepository: RecipesRepository {
    override suspend fun getRecipes(): List<Recipe> = FakeDataSource.recipesList
}