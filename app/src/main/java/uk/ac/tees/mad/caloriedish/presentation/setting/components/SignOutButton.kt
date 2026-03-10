package uk.ac.tees.mad.caloriedish.presentation.setting.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignOutButton(modifier: Modifier = Modifier,
                  onSignOutClick:()-> Unit,
                  isLoading : Boolean){
    OutlinedButton(
        modifier = modifier.fillMaxWidth() ,
        onClick = onSignOutClick
    ) {
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp)
            )
        }else{
            Text(
                text = "Sign out"
            )
        }
    }
}