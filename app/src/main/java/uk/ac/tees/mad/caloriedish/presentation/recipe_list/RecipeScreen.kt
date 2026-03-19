package uk.ac.tees.mad.caloriedish.presentation.recipe_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishEntity
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.presentation.home.NutrientItem
import uk.ac.tees.mad.caloriedish.presentation.recipe_list.component.RecipeTopBar
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens
import uk.ac.tees.mad.caloriedish.utils.toFoodNutrition

@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel = viewModel() ,
    onDetailClick: (FoodNutrition) -> Unit
) {

    val uiState by viewModel.recipeUiState.collectAsStateWithLifecycle()

    RecipeScreenContent(
        uiState = uiState,
        onDeleteClick = {
            viewModel.deleteDish(it)
        },
        onDetailClick = onDetailClick
    )
}


@Composable
fun RecipeScreenContent(
    uiState: RecipeUiState,
    onDeleteClick: (Long) -> Unit ,
    onDetailClick: (FoodNutrition) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RecipeTopBar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
        )
        LazyColumn(modifier =  Modifier
            .fillMaxSize()
            .padding(start = Dimens.paddingMedium ,end = Dimens.paddingMedium , bottom = Dimens.bottomBarHeight + Dimens.paddingSmall)
            .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(Dimens.paddingSmall)) {

            items(
                uiState.recipeList
            ){item->
                RecipeItem(
                    nutrition = item,
                    onDetailClick = onDetailClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}



@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    nutrition: FavoriteDishEntity,
    onDetailClick: (FoodNutrition) -> Unit,
    onDeleteClick: (Long) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable{
                onDetailClick(
                    nutrition.toFoodNutrition()
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ) ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevationSmall
        ) ,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.paddingLarge),

            verticalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = nutrition.nutrition.foodName,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${String.format("%.2f", nutrition.nutrition.weight)} ${nutrition.nutrition.weightUnit}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                IconButton(onClick = {
                    onDeleteClick(nutrition.id)
                } ,
                    modifier = Modifier.padding(horizontal = Dimens.paddingSmall)) {
                    Icon(
                        imageVector = Icons.Default.Delete ,
                        contentDescription = "delete"
                    )
                }
            }

            Text(
                text = "${String.format("%.2f", nutrition.nutrition.calories.value)} ${nutrition.nutrition.calories.unit}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Divider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                NutrientItem(
                    label = "Protein",
                    value = nutrition.nutrition.protein.value,
                    unit = nutrition.nutrition.protein.unit
                )

                NutrientItem(
                    label = "Fat",
                    value = nutrition.nutrition.fat.value,
                    unit = nutrition.nutrition.fat.unit
                )

                NutrientItem(
                    label = "Carbs",
                    value = nutrition.nutrition.carbs.value,
                    unit = nutrition.nutrition.carbs.unit
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun RecipeScreenPreview() {
    RecipeScreenContent(
        uiState = RecipeUiState(),
        onDeleteClick = {} ,
        onDetailClick = {}
    )
}
