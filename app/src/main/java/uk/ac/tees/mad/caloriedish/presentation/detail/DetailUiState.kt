package uk.ac.tees.mad.caloriedish.presentation.detail

import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

data class DetailUiState(
    val foodNutrition: FoodNutrition ? = null
)