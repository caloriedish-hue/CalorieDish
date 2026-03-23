package uk.ac.tees.mad.caloriedish.presentation.home

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.caloriedish.data.local.RecentSearchEntity
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.presentation.home.components.HomeTopBar
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens
import java.util.Locale


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel() ,
               onDetailClick:(FoodNutrition)-> Unit) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val matches =
                    result.data?.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    )
                matches?.get(0)?.let { spokenText ->
                    viewModel.onQueryChange(spokenText)
                    viewModel.onSearch()
                }
            }
        }

    fun startVoiceSearch() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Speak food name"
        )
        launcher.launch(intent)
    }




    HomeScreenContent(
        uiState = uiState,
        onQueryChange = viewModel::onQueryChange,
        onSuggestionSelected = viewModel::onSuggestionSelected,
        onSearch = viewModel::onSearch,
        onClearAllClick = viewModel::onClearAllCLick,
        onFavoriteClick = viewModel::onFavoriteClick ,
        onSearchIconClick = viewModel::onSearchIconClick ,
        onDetailClick = onDetailClick ,
        onVoiceClick = {
            startVoiceSearch()
        }
    )
}


@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onQueryChange: (String) -> Unit,
    onSuggestionSelected: (String) -> Unit,
    onSearch: () -> Unit,
    onClearAllClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onSearchIconClick: (String) -> Unit ,
    onDetailClick:(FoodNutrition)-> Unit ,
    onVoiceClick :()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = Dimens.bottomBarHeight)
    ) {
        HomeTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            suggestionList = uiState.suggestions,
            query = uiState.query,
            onQueryChange = onQueryChange,
            onSelect = onSuggestionSelected,
            onSearch = onSearch ,
            onVoiceClick = onVoiceClick
        )

        uiState.foodNutrition?.let { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "latest Result",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Details",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.clickable {
                        onDetailClick(item)
                    }
                )
                Spacer(
                    modifier = Modifier.height(Dimens.marginMedium)
                )
            }
            LatestFetchedItem(nutrition = item ,
                onFavoriteClick = onFavoriteClick ,
                isFavorite = uiState.isAddedToFavorite)

        }
        uiState.recentSearch?.let { it ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Search History",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Clear All",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.clickable {
                        onClearAllClick()
                    }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = Dimens.paddingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.paddingSmall)
            ) {
                items(it) { item ->
                    RecentItem(
                        recentSearch  = item ,
                        onSearchIconClick = onSearchIconClick
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreenContent(
        uiState = HomeUiState(),
        onQueryChange = {},
        onSuggestionSelected = {},
        onSearch = {},
        onClearAllClick = {},
        onSearchIconClick = {},
        onFavoriteClick = {} ,
        onDetailClick = {} ,
        onVoiceClick = {}
    )
}


@Composable
fun RecentItem(modifier: Modifier = Modifier ,
               recentSearch: RecentSearchEntity ,
               onSearchIconClick: (String) -> Unit
) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevationSmall
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.paddingSmall),
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = recentSearch.query
            )
            IconButton(
                onClick = { onSearchIconClick(recentSearch.query) }
            ) {
                Icon(
                    imageVector = Icons.Default.Search ,
                    contentDescription = "search"
                )
            }
        }
    }
}



@Composable
fun LatestFetchedItem(
    modifier: Modifier = Modifier,
    nutrition: FoodNutrition ,
    isFavorite: Boolean ,
    onFavoriteClick:()-> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.paddingMedium),

        shape = MaterialTheme.shapes.medium,

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevationSmall
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.paddingLarge),

            verticalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
        ) {

            // Food Name + Weight
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = nutrition.foodName,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${String.format("%.2f", nutrition.weight)} ${nutrition.weightUnit}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Calories Highlight
            Text(
                text = "${String.format("%.2f", nutrition.calories.value)} ${nutrition.calories.unit}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Divider()

            // Macronutrients Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                NutrientItem(
                    label = "Protein",
                    value = nutrition.protein.value,
                    unit = nutrition.protein.unit
                )

                NutrientItem(
                    label = "Fat",
                    value = nutrition.fat.value,
                    unit = nutrition.fat.unit
                )

                NutrientItem(
                    label = "Carbs",
                    value = nutrition.carbs.value,
                    unit = nutrition.carbs.unit
                )
            }

            Spacer(modifier = Modifier.height(Dimens.paddingSmall))

            Button(
                modifier = Modifier.fillMaxWidth() ,
                onClick = onFavoriteClick ,
                enabled = !isFavorite
            ) {
                Text(
                    text = "Add to Favorite"
                )
            }
        }
    }
}



@Composable
fun NutrientItem(
    label: String,
    value: Double,
    unit: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "${String.format("%.2f", value)} $unit",
            style = MaterialTheme.typography.titleMedium
        )
    }
}