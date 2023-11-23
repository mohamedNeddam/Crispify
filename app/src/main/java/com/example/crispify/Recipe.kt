package com.example.crispify

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("cook_time")
    val cookTime: Int,
    val description: String,
    val directions: List<String>,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val ingredients: List<String>,
    val owner: String,
    @SerializedName("prep_time")
    val prepTime: Int,
    val rating: Double,
    val serving: Int,
    val title: String,
    val yield: String
)