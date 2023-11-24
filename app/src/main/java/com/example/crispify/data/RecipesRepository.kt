package com.example.crispify.data

import com.example.crispify.Recipe
import com.example.crispify.network.RecipeApi

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}

class NetworkRecipesRepository : RecipesRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return RecipeApi.retrofitService.getAllRecipes()
    }

}