package com.example.crispify.fake

import com.example.crispify.Recipe
import com.example.crispify.network.RecipeServiceApi

class FakeRecipesServiceApi: RecipeServiceApi {
    override suspend fun getAllRecipes(): List<Recipe> = FakeDataSource.recipesList
}