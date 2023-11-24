package com.example.crispify

import com.example.crispify.data.NetworkRecipesRepository
import com.example.crispify.fake.FakeDataSource
import com.example.crispify.fake.FakeRecipesServiceApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkRecipesRepositoryTest {

    @Test
    fun networkRecipesRepositoryTest_getRecipes_verifyRecipesList() = runTest {
        val repository = NetworkRecipesRepository(
            recipeServiceApi = FakeRecipesServiceApi()
        )

        assertEquals(repository.getRecipes(), FakeDataSource.recipesList)
    }
}