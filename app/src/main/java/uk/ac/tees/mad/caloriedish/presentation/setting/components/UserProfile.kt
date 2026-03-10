package uk.ac.tees.mad.caloriedish.presentation.setting.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens


@Composable
fun UserProfile(modifier : Modifier = Modifier ,
                userProfile : String){
    Card(modifier = modifier.fillMaxWidth() ,
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ) ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevationSmall
        ) ,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline)
    ) {
        Row (modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = Dimens.paddingSmall) ,
            verticalAlignment = Alignment.CenterVertically){

           IconButton(onClick = {} ,
               colors = IconButtonDefaults.iconButtonColors(
                   containerColor = MaterialTheme.colorScheme.surface
               ),
               modifier  = Modifier.size(48.dp)) {
               Icon(
                   imageVector = Icons.Default.Person ,
                   contentDescription = null,
                   tint = MaterialTheme.colorScheme.onSurface ,
                   modifier = Modifier.size(24.dp)
               )
           }
            Spacer(modifier = Modifier.width(Dimens.paddingSmall))
            Text(
                text = userProfile,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface ,
                modifier = Modifier.weight(1f)
            )
        }
    }
}