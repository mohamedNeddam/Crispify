package com.example.airfryerrecipes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarNavigation(
         "home",
        "Home",
        Icons.Default.Home
    )

    object FavScreen: BottomBarNavigation(
        "fav_screen",
        "Favorite",
        Icons.Default.Favorite
    )

    object SecondScreen: BottomBarNavigation(
        "second_screen",
        "Second",
        Icons.Default.Settings
    )
}