package uk.ac.tees.mad.caloriedish.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigationHost(
    startDestination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController
){

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(route = NavigationRoutes.HomeScreen.route){

        }

        composable(NavigationRoutes.SplashScreen.route){

        }

        composable(NavigationRoutes.LoginScreen.route){

        }

        composable(NavigationRoutes.SettingsScreen.route){

        }

        composable(NavigationRoutes.CalorieDetailsScreen.route){

        }

        composable(NavigationRoutes.RegisterScreen.route){

        }

    }
}