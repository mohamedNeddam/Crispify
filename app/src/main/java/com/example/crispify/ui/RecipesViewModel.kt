package com.example.crispify.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crispify.Recipe
import com.example.crispify.data.NetworkRecipesRepository
import com.example.crispify.network.RecipeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipesViewModel: ViewModel() {

    val recipesList: MutableState<RecipesState> = mutableStateOf(RecipesState.Loading)

    init {
        getRecipes()
    }
    private fun getRecipes(){
        viewModelScope.launch() {
            try {
                val remoteRecipes = getRemoteRecipes()
                recipesList.value = RecipesState.Success(remoteRecipes)
            }catch (exception : Exception){
                recipesList.value = RecipesState.Error
            }
        }
    }

    private suspend fun getRemoteRecipes() = withContext(Dispatchers.IO){
        NetworkRecipesRepository().getRecipes()
    }
}

sealed interface RecipesState{
    data class Success(val recipes: List<Recipe>) : RecipesState
    object Error : RecipesState
    object Loading : RecipesState

}