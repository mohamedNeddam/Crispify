package com.example.crispify.network

import com.example.crispify.Recipe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RecipeServiceApi {
    @GET("recipes")
    suspend fun getAllRecipes(): List<Recipe>
}