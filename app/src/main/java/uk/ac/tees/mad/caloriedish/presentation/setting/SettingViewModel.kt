package uk.ac.tees.mad.caloriedish.presentation.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.caloriedish.CalorieDishApp
import uk.ac.tees.mad.caloriedish.domain.usecase.DeleteOnLogoutUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.LogoutUseCase
import uk.ac.tees.mad.caloriedish.utils.PreferenceManager

class SettingViewModel(application: Application) :
    AndroidViewModel(application) {
    private val preferenceManager: PreferenceManager =
        (application as CalorieDishApp).dependencyContainer.preferenceManager

    private val logoutUseCase: LogoutUseCase =
        (application as CalorieDishApp).dependencyContainer.logoutUseCase
    private val deleteOnLogoutUseCase: DeleteOnLogoutUseCase =
        (application as CalorieDishApp).dependencyContainer.deleteOnLogoutUseCase
    private val _settingUiState = MutableStateFlow(SettingUiState())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState.asStateFlow()


    init {
        resolveProfile()
    }

    private fun resolveProfile() {
        _settingUiState.value = _settingUiState.value.copy(
            isLoading = true
        )
        val userProfile = preferenceManager.getUserProfile()
        _settingUiState.value = _settingUiState.value.copy(
            isLoading = false, userEmail = userProfile ?: "no profile"
        )
    }

     fun onLogoutClick() {
        viewModelScope.launch {
            _settingUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            logoutUseCase().onFailure { error ->
                _settingUiState.update {
                    it.copy(
                        isLoading = false, error = error.message, navigateToLogin = false
                    )
                }
            }.onSuccess {
                preferenceManager.setLoggedIn(false)
                preferenceManager.setUserProfile(null)
                deleteOnLogoutUseCase()
                _settingUiState.update {
                    it.copy(
                        isLoading = false, navigateToLogin = true, error = null
                    )
                }
            }
        }
    }
}