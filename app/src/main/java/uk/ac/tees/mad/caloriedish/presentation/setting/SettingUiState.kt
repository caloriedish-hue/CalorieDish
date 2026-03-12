package uk.ac.tees.mad.caloriedish.presentation.setting

data class SettingUiState(
    val isLoading : Boolean = false ,
    val userEmail : String = ""  ,
    val error :String ? = null ,
    val navigateToLogin : Boolean = false
)