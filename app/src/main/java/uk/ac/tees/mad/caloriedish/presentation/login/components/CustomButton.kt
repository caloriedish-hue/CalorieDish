package uk.ac.tees.mad.caloriedish.presentation.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomAuthButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean,
    isLoginEnabled: Boolean,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        enabled = !isLoading && isLoginEnabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.onPrimary
        )
    ) {
        when{
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp) ,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }else -> {
                Text(
                    text = text,
                    fontFamily = FontFamily.Cursive ,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}