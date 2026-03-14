package uk.ac.tees.mad.caloriedish.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: NavigationRoutes,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = NavigationRoutes.HomeScreen,
        label = "Home",
        icon = Icons.Default.Home
    )

    object Recipes : BottomNavItem(
        route = NavigationRoutes.RecipesScreen,
        label = "Recipes",
        icon = Icons.Default.Restaurant
    )

    object Settings : BottomNavItem(
        route = NavigationRoutes.SettingsScreen,
        label = "Settings",
        icon = Icons.Default.Settings
    )
}