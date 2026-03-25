package uk.ac.tees.mad.caloriedish.presentation.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.caloriedish.presentation.setting.components.SettingsTopBar
import uk.ac.tees.mad.caloriedish.presentation.setting.components.SignOutButton
import uk.ac.tees.mad.caloriedish.presentation.setting.components.UserProfile
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens

@Composable
fun SettingScreen(
    viewModel: SettingViewModel = viewModel() ,
    onNavigateToLoginScreen : () -> Unit
){
    val uiState by viewModel.settingUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateToLogin) {
        if (uiState.navigateToLogin){
            onNavigateToLoginScreen()
        }
    }

    SettingScreenContent(
        uiState = uiState ,
        onLogoutClick = viewModel::onLogoutClick
    )
}

@Composable
fun SettingScreenContent(uiState: SettingUiState, onLogoutClick: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        SettingsTopBar()
        Column (modifier =  Modifier
            .weight(1f)
            .padding(horizontal = Dimens.paddingMedium)){
            Spacer(modifier = Modifier.height(Dimens.paddingSmall))
            UserProfile(userProfile = uiState.userEmail)
            Spacer(modifier = Modifier.weight(1f))
            SignOutButton(onSignOutClick = onLogoutClick, isLoading = uiState.isLoading , modifier = Modifier)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(
){
SettingScreenContent(
    uiState = SettingUiState(),
    onLogoutClick = {})
}