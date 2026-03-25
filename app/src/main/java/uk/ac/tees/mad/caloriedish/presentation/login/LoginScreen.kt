package uk.ac.tees.mad.caloriedish.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.caloriedish.presentation.login.components.CustomAuthButton
import uk.ac.tees.mad.caloriedish.presentation.login.components.CustomTextField
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit
) {

    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) {
            onNavigateToHomeScreen()
        }
    }

    LoginScreenContent(
        uiState = uiState,
        onLoginCLick = viewModel::onLoginClick,
        onRegisterClick = onNavigateToRegisterScreen,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    onLoginCLick: () -> Unit,
    onRegisterClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.paddingXLarge)
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(Dimens.paddingSmall)
        ) {

            Text(
                text = "Login Now",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.email,
                label = "Email",
                trailingIcon = Icons.Default.Email,
                onValueChange = onEmailChange,
            )

            Spacer(modifier = Modifier.height(Dimens.paddingXLarge))

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                label = "Password",
                trailingIcon = Icons.Default.Visibility,
                onValueChange = onPasswordChange,
            )

            Spacer(modifier = Modifier.height(Dimens.paddingXLarge))

            CustomAuthButton(
                onClick = onLoginCLick,
                isLoading = uiState.isLoading,
                isLoginEnabled = uiState.isLoginEnabled ,
                text = "Login",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Dont have an account? Register",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onRegisterClick()
                    },
                color = MaterialTheme.colorScheme.onPrimary,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(Dimens.paddingXLarge))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreenContent(
        uiState = LoginUiState(),
        onLoginCLick = {},
        onEmailChange = {},
        onRegisterClick = {},
        onPasswordChange = {}
    )
}