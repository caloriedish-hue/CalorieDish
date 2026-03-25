package uk.ac.tees.mad.caloriedish.presentation.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.caloriedish.CalorieDishApp
import uk.ac.tees.mad.caloriedish.domain.usecase.RegisterUseCase
import uk.ac.tees.mad.caloriedish.utils.PreferenceManager

class SignUpViewModel(application: Application):
AndroidViewModel(application){

    private val preferenceManager : PreferenceManager =
        (application as CalorieDishApp).dependencyContainer.preferenceManager
    private val registerUseCase : RegisterUseCase =
        (application as CalorieDishApp).dependencyContainer.registerUseCase

    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState = _signUpUiState.asStateFlow()
    fun onEmailChange(email : String){
        _signUpUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordChange(password : String){
        _signUpUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword : String) {
        _signUpUiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }

    fun onSignUpClick(){

        viewModelScope.launch {
            _signUpUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            val state = _signUpUiState.value

            registerUseCase(email = state.email , password = state.password)
                .onSuccess {
                    preferenceManager.setLoggedIn(true)
                    preferenceManager.setUserProfile(state.email)
                    _signUpUiState.update {
                        it.copy(
                            isLoading = false ,
                            isSignUpSuccess = true ,
                            error = null
                        )
                    }
                }
                .onFailure {error->
                    _signUpUiState.update {
                        it.copy(
                            isLoading = false ,
                            isSignUpSuccess = false ,
                            error = error.message
                        )
                    }
                }
        }
    }
}
