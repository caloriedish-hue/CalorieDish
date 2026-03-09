package uk.ac.tees.mad.caloriedish.presentation.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.ac.tees.mad.caloriedish.CalorieDishApp
import uk.ac.tees.mad.caloriedish.utils.PreferenceManager

class SplashViewModel (application: Application): AndroidViewModel(application){
    private val preferenceManager: PreferenceManager =
        (application as CalorieDishApp).dependencyContainer.preferenceManager

    private val _splashUiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()


    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        when{
            preferenceManager.getLoggedIn() -> {
                _splashUiState.value = SplashUiState.LoggedIn
            }
            else -> {
                _splashUiState.value = SplashUiState.LoggedOut
            }
        }
    }
}