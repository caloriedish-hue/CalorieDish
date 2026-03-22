package uk.ac.tees.mad.caloriedish.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    suggestionList: List<String>,
    query: String,
    onSelect: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onVoiceClick: () -> Unit,
) {

    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingLarge, vertical = Dimens.paddingSmall),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {
            onSearch()
            active = false
        },
        active = active,
        onActiveChange = { active = it },

        placeholder = { Text("Search recipes...") },

        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },

        trailingIcon = {
            Icon(
                Icons.Default.KeyboardVoice,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onVoiceClick()
                })
        },

        colors = androidx.compose.material3.SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {
        LazyColumn {
            items(suggestionList.size) { index ->
                SuggestedItem(
                    label = suggestionList[index],
                    onSelect = onSelect
                )
            }
        }
    }
}


@Composable
fun SuggestedItem(
    label: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.paddingSmall)
            .clickable {
                onSelect(label)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.paddingSmall)
        )
    }
}