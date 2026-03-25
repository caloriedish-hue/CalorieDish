package uk.ac.tees.mad.caloriedish.navigation

sealed class NavigationRoutes (val route: String){
    object SplashScreen : NavigationRoutes("splash_screen")
    object LoginScreen : NavigationRoutes("login_screen")
    object RegisterScreen : NavigationRoutes("register_screen")
    object HomeScreen : NavigationRoutes("home_screen")
    object CalorieDetailsScreen : NavigationRoutes("calorie_details_screen/{food}"){
        fun createRoute(food: String) = "calorie_details_screen/$food"
    }
    object SettingsScreen : NavigationRoutes("settings_screen")
    object RecipesScreen : NavigationRoutes("recipes_screen")

}