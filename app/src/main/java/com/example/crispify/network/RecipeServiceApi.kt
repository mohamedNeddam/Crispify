package com.example.crispify.network

import com.example.crispify.Recipe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://recipe-mock-api.onrender.com/api/"



interface RecipeServiceApi {
    @GET("recipes")
    suspend fun getAllRecipes(): List<Recipe>
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(
        GsonConverterFactory.create()
    ).build()


object RecipeApi {
    val retrofitService : RecipeServiceApi by lazy {
        retrofit.create(RecipeServiceApi::class.java)
    }
}
