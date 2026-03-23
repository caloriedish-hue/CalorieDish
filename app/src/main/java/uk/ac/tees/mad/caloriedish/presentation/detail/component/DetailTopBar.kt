package uk.ac.tees.mad.caloriedish.presentation.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens

@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    isSpeaking: Boolean,
    onBackClick: () -> Unit ,
    onSpeechToggle: () -> Unit ,
    icon : ImageVector ,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.Bottom
    ) {


        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(start = Dimens.paddingSmall)
                .clickable {
                    onBackClick()
                }
        )


        Text(
            text = "Nutrition Details",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = icon,
            contentDescription = "Mute" ,
            modifier = Modifier
                .padding(end = Dimens.paddingMedium)
                .clickable{
                    onSpeechToggle()
                },
        )

    }
}