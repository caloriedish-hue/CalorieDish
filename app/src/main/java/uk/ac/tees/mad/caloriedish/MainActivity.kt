package uk.ac.tees.mad.caloriedish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.caloriedish.navigation.AppNavigationHost
import uk.ac.tees.mad.caloriedish.navigation.NavigationRoutes
import uk.ac.tees.mad.caloriedish.presentation.splash.SplashUiState
import uk.ac.tees.mad.caloriedish.presentation.splash.SplashViewModel
import uk.ac.tees.mad.caloriedish.presentation.theme.CalorieDishTheme
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashViewModel: SplashViewModel by viewModels()
        splashScreen.setKeepOnScreenCondition {
            splashViewModel.splashUiState.value is SplashUiState.Loading
        }
        setContent {
            val navController = rememberNavController()
            val startDestination =
                when (splashViewModel.splashUiState.collectAsStateWithLifecycle().value) {
                    SplashUiState.LoggedOut -> NavigationRoutes.LoginScreen.route
                    SplashUiState.LoggedIn -> NavigationRoutes.HomeScreen.route
                    else -> null
                }
            startDestination?.let {
                CalorieDishTheme {
                    AppNavigationHost(
                        startDestination = startDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}
