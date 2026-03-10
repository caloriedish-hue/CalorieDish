package uk.ac.tees.mad.caloriedish.presentation.signup

data class SignUpUiState(
    val isLoading : Boolean = false,
    val isSignUpSuccess : Boolean = false,
    val email : String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val error : String ? = null
){
    val isSignUpEnabled : Boolean
        get() = email.isNotBlank()
                && password.isNotBlank()
                && confirmPassword.isNotBlank()
                && password == confirmPassword
                && password.length>=8
}
