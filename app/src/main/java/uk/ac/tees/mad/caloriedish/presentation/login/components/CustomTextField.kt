package uk.ac.tees.mad.caloriedish.presentation.login.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomTextField(modifier : Modifier = Modifier,
                    value : String,
                    label : String ,
                    trailingIcon : ImageVector ? = null ,
                    onValueChange : (String) -> Unit){

    TextField(
        modifier = modifier,
        value = value ,
        onValueChange = onValueChange ,
        maxLines = 1 ,
        singleLine = true ,
        placeholder = {
            Text(
                text = label
            )
        } ,
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    imageVector = trailingIcon ,
                    contentDescription = "trailing Icon"
                )
            }
        } ,
        colors =  TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiary
        ),
    )
}

