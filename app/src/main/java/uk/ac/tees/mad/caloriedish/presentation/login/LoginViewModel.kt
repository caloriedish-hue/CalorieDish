package uk.ac.tees.mad.caloriedish.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.caloriedish.CalorieDishApp
import uk.ac.tees.mad.caloriedish.domain.usecase.LoadOnLoginUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.LoginUseCase
import uk.ac.tees.mad.caloriedish.utils.PreferenceManager

class LoginViewModel(application: Application)
    : AndroidViewModel(application) {
        private val loginUseCase : LoginUseCase =
            (application as CalorieDishApp).dependencyContainer.loginUseCase

    private  val loadOnLoginUseCase : LoadOnLoginUseCase =
        (application as CalorieDishApp).dependencyContainer.loadOnLoginUseCase

        private val preferenceManager : PreferenceManager =
            (application as CalorieDishApp).dependencyContainer.preferenceManager
        private val _loginUiState = MutableStateFlow(LoginUiState())
       val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    fun onEmailChange(email: String){
        _loginUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordChange(password: String){
        _loginUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onLoginClick(){
        viewModelScope.launch {
            _loginUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val state = _loginUiState.value
            loginUseCase(email = state.email , password =  state.password)
                .onSuccess {
                    preferenceManager.setLoggedIn(true)
                    preferenceManager.setUserProfile(state.email)
                    loadOnLoginUseCase()
                    _loginUiState.update {
                        it.copy(
                            isLoading = false  ,
                            isLoginSuccess = true ,
                            error = null
                        )
                    }
                }
                .onFailure {error->
                    _loginUiState.update {
                        it.copy(
                            isLoading = false  ,
                            isLoginSuccess = false,
                            error = error.message
                        )
                    }
                }
        }
    }
}