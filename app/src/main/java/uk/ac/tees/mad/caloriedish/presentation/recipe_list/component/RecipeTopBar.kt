package uk.ac.tees.mad.caloriedish.presentation.recipe_list.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeTopBar(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Favorites",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

