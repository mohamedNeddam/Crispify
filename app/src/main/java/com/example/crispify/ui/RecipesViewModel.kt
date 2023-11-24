package com.example.crispify.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.crispify.CrispifyApplication
import com.example.crispify.Recipe
import com.example.crispify.data.NetworkRecipesRepository
import com.example.crispify.data.RecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipesViewModel(private val recipesRepository: RecipesRepository): ViewModel() {

    var recipesList: RecipesState by mutableStateOf(RecipesState.Loading)
        private set

    init {
        getRecipes()
    }
    private fun getRecipes(){
        viewModelScope.launch() {
            recipesList = try {
                val remoteRecipes = getRemoteRecipes()
                RecipesState.Success(remoteRecipes)
            }catch (exception : Exception){
                RecipesState.Error
            }
        }
    }

    private suspend fun getRemoteRecipes() = withContext(Dispatchers.IO){
        recipesRepository.getRecipes()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CrispifyApplication)
                val recipesRepository = application.container.recipesRepository
                RecipesViewModel(recipesRepository = recipesRepository)
            }
        }
    }

}

sealed interface RecipesState{
    data class Success(val recipes: List<Recipe>) : RecipesState
    object Error : RecipesState
    object Loading : RecipesState

}