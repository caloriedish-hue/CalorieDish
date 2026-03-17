package uk.ac.tees.mad.caloriedish.presentation.home

import uk.ac.tees.mad.caloriedish.data.local.RecentSearchEntity
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

data class HomeUiState(
    val isLoading: Boolean = false,
    val isAddedToFavorite : Boolean = false ,
    val query: String = "" ,
    val suggestions: List<String> = emptyList(),
    val foodNutrition: FoodNutrition? = null,
    val error: String? = null ,
    val recentSearch :List<RecentSearchEntity>? = null
)