package uk.ac.tees.mad.caloriedish.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.domain.model.Nutrient
import uk.ac.tees.mad.caloriedish.presentation.detail.component.DetailTopBar
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens


@Composable
fun DetailScreen(foodNutrition: FoodNutrition ,
                 onBackClick:()-> Unit  ,
                 viewModel: DetailViewModel = viewModel()
){

    LaunchedEffect(foodNutrition) {
        viewModel.setFoodNutrition(foodNutrition)
    }

    val uiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    DetailScreenContent(
        nutrition = uiState.foodNutrition ,
        onBackClick = onBackClick
    )

}





@Composable
fun DetailScreenContent(
    nutrition: FoodNutrition?,
    onBackClick:()-> Unit ,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DetailTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary) ,
            onBackClick = onBackClick
        )

        nutrition?.let {nutrition ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens.paddingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.paddingLarge)
            ) {

                FoodHeader(nutrition)

                CaloriesCard(nutrition)

                MacroSection(nutrition)

                NutrientSection(nutrition)
            }
        }
    }
}

@Composable
fun FoodHeader(nutrition: FoodNutrition) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = nutrition.foodName.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Serving: ${nutrition.measure} (${String.format("%.2f", nutrition.weight)} ${nutrition.weightUnit})",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun CaloriesCard(nutrition: FoodNutrition) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevationSmall
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Calories",
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "${String.format("%.2f", nutrition.calories.value)} ${nutrition.calories.unit}",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun MacroSection(nutrition: FoodNutrition) {

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)) {

        Text(
            text = "Macronutrients",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MacroCard(
                modifier = Modifier.weight(1f),
                label = "Protein",
                nutrient = nutrition.protein
            )

            MacroCard(
                modifier = Modifier.weight(1f),
                label = "Carbs",
                nutrient = nutrition.carbs
            )

            MacroCard(
                modifier = Modifier.weight(1f),
                label = "Fat",
                nutrient = nutrition.fat
            )
        }
    }
}


@Composable
fun MacroCard(
    modifier: Modifier = Modifier,
    label: String,
    nutrient: Nutrient
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "${String.format("%.2f", nutrient.value)} ${nutrient.unit}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun NutrientSection(nutrition: FoodNutrition) {

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)) {

        Text(
            text = "Other Nutrients",
            style = MaterialTheme.typography.titleMedium
        )

        NutrientProgressCard("Fiber", nutrition.fiber)

        NutrientProgressCard("Sugar", nutrition.sugar)
    }
}


@Composable
fun NutrientProgressCard(
    label: String,
    nutrient: Nutrient
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(label)

                Text(
                    "${String.format("%.2f", nutrient.value)} ${nutrient.unit}"
                )
            }

            LinearProgressIndicator(
                progress = (nutrient.value / 100)
                    .coerceIn(0.0, 1.0)
                    .toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    DetailScreenContent(
        nutrition = FoodNutrition(
            foodName = "",
            measure = "",
            weight = 0.0,
            weightUnit = "",
            calories = Nutrient(
                value = 0.0,
                unit = ""
            ),
            protein =  Nutrient(
                value = 0.0,
                unit = ""
            ),
            carbs =  Nutrient(
                value = 0.0,
                unit = ""
            ),
            fat =  Nutrient(
                value = 0.0,
                unit = ""
            ),
            fiber =  Nutrient(
                value = 0.0,
                unit = ""
            ),
            sugar =  Nutrient(
                value = 0.0,
                unit = ""
            )
        ),
        modifier = Modifier ,
        onBackClick = {}
    )
}

