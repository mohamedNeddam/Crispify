package com.example.crispify.data

import com.example.crispify.Recipe
import com.example.crispify.network.RecipeApi
import com.example.crispify.network.RecipeServiceApi

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}

class NetworkRecipesRepository(
    private val recipeServiceApi: RecipeServiceApi
) : RecipesRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return recipeServiceApi.getAllRecipes()
    }

}