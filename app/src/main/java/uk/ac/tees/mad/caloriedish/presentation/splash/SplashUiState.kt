package uk.ac.tees.mad.caloriedish.presentation.splash

sealed class SplashUiState{
    object Loading : SplashUiState()
    object LoggedIn : SplashUiState()
    object LoggedOut : SplashUiState()
}
