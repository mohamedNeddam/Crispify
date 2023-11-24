package com.example.crispify.data

import com.example.crispify.network.RecipeServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val recipesRepository: RecipesRepository
}

class DefaultAppContainer: AppContainer{

    private val BASE_URL =
        "https://recipe-mock-api.onrender.com/api/"


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val retrofitService : RecipeServiceApi by lazy {
        retrofit.create(RecipeServiceApi::class.java)
    }

    override val recipesRepository: RecipesRepository by lazy {
        NetworkRecipesRepository(retrofitService)
    }

}