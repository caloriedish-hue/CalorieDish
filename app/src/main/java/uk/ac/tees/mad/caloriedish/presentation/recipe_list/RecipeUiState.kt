package uk.ac.tees.mad.caloriedish.presentation.recipe_list

import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishEntity

data class RecipeUiState(
    val isLoading: Boolean  = false,
    val error: String ? = null ,
    val recipeList: List<FavoriteDishEntity> = emptyList()
)
