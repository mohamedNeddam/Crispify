package com.example.airfryerrecipes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crispify.ui.screens.FavScreen
import com.example.crispify.ui.screens.HomeScreen
import com.example.crispify.ui.screens.SecondScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarNavigation.Home.route){
        composable(BottomBarNavigation.Home.route){
            HomeScreen()
        }
        composable(BottomBarNavigation.FavScreen.route){
            FavScreen()
        }
        composable(BottomBarNavigation.SecondScreen.route){
            SecondScreen()
        }
    }
}