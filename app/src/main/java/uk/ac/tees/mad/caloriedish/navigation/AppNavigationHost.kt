package uk.ac.tees.mad.caloriedish.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.presentation.detail.DetailScreen
import uk.ac.tees.mad.caloriedish.presentation.home.HomeScreen
import uk.ac.tees.mad.caloriedish.presentation.login.LoginScreen
import uk.ac.tees.mad.caloriedish.presentation.recipe_list.RecipeScreen
import uk.ac.tees.mad.caloriedish.presentation.setting.SettingScreen
import uk.ac.tees.mad.caloriedish.presentation.signup.SignUpScreen
import java.net.URLEncoder

@Composable
fun AppNavigationHost(
    startDestination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Log.d("TAG" , "AppNavigationHost: $startDestination")

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarScreens = listOf(
        NavigationRoutes.HomeScreen.route,
        NavigationRoutes.RecipesScreen.route
    )

    Box(modifier
        .fillMaxSize()
        .navigationBarsPadding()
        .background(color = MaterialTheme.colorScheme.background)){
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = NavigationRoutes.HomeScreen.route) {
                HomeScreen(
                    onDetailClick = { it->
                        val json = Json.encodeToString(it)
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate(NavigationRoutes.CalorieDetailsScreen.createRoute(encoded))
                    }
                )
            }
            composable(NavigationRoutes.LoginScreen.route) {
                LoginScreen(
                    onNavigateToRegisterScreen = {
                        navController.navigate(NavigationRoutes.RegisterScreen.route)
                    },
                    onNavigateToHomeScreen = {
                        navController.navigate(NavigationRoutes.HomeScreen.route){
                            popUpTo(NavigationRoutes.LoginScreen.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(NavigationRoutes.SettingsScreen.route) {
                SettingScreen(
                    onNavigateToLoginScreen = {
                        navController.navigate(NavigationRoutes.LoginScreen.route){
                            popUpTo(NavigationRoutes.HomeScreen.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(NavigationRoutes.RecipesScreen.route){
                RecipeScreen(
                    onDetailClick = { it->
                        val json = Json.encodeToString(it)
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate(NavigationRoutes.CalorieDetailsScreen.createRoute(encoded))
                    }
                )
            }

            composable(NavigationRoutes.CalorieDetailsScreen.route ,
                arguments = listOf(navArgument("food") { type = NavType.StringType })) {backStackEntry->
                val json = backStackEntry.arguments?.getString("food")!!
                val food = Json.decodeFromString<FoodNutrition>(json)
                DetailScreen(
                    foodNutrition = food ,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(NavigationRoutes.RegisterScreen.route) {
                SignUpScreen(
                    onLoginClick = {
                        navController.popBackStack()
                    } ,
                    onNavigateHome = {
                        navController.navigate(NavigationRoutes.HomeScreen.route){
                            popUpTo(NavigationRoutes.RegisterScreen.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        if(currentRoute in bottomBarScreens){
            BottomBar(navController = navController ,
             currentRoute =    currentRoute ,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
