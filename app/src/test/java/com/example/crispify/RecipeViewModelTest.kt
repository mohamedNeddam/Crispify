package com.example.crispify

import com.example.crispify.fake.FakeDataSource
import com.example.crispify.fake.FakeNetworkRecipesRepository
import com.example.crispify.rules.TestDispatcherRule
import com.example.crispify.ui.RecipesState
import com.example.crispify.ui.RecipesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RecipeViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun recipesViewModel_getRecipes_verifyRecipeUiStateSuccess() = runBlockingTest  {
        val recipeRepository = FakeNetworkRecipesRepository()
        val recipeViewModel = RecipesViewModel(recipeRepository)

        delay(1000)

        assertEquals(
            RecipesState.Success(FakeDataSource.recipesList),
            recipeViewModel.recipesList
        )
    }
}