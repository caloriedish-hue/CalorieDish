package uk.ac.tees.mad.caloriedish.presentation.login

import uk.ac.tees.mad.caloriedish.utils.isValid

data class LoginUiState(
    val isLoading : Boolean = false ,
    val email : String = "" ,
    val password : String = "" ,
    val error : String ? = null ,
    val isLoginSuccess : Boolean = false
){
    val isLoginEnabled : Boolean
        get() = email.isNotBlank()
                &&password.length >= 8
                && password.isNotBlank()
                && email.isValid()
}