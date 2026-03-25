package uk.ac.tees.mad.caloriedish.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    suggestionList: List<String>,
    query: String,
    onSelect: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit ,
    onVoiceClick:()-> Unit
) {

    Row(
        modifier = modifier
            .padding(vertical = Dimens.paddingMedium),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomSearchBar(
            suggestionList = suggestionList ,
            query = query ,
            onSelect = onSelect ,
            onQueryChange = onQueryChange ,
            onSearch  = onSearch ,
            onVoiceClick = onVoiceClick
        )
    }
}