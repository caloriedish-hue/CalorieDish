package uk.ac.tees.mad.caloriedish.presentation.recipe_list

import android.app.Application
import androidx.compose.ui.graphics.vector.Path
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.caloriedish.CalorieDishApp
import uk.ac.tees.mad.caloriedish.domain.usecase.DeleteFavoriteUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.FetchFavoriteUseCase

class RecipeViewModel(application: Application) :
    AndroidViewModel(application) {
    private val fetchFavoriteUseCase: FetchFavoriteUseCase =
        (application as CalorieDishApp).dependencyContainer.fetchFavoriteUseCase
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase =
        (application as CalorieDishApp).dependencyContainer.deleteFavoriteUseCase



    private val _recipeUiState = MutableStateFlow(RecipeUiState())
    val recipeUiState = _recipeUiState.asStateFlow()

    init {
        fetchFavoriteRecipes()
    }

    private fun fetchFavoriteRecipes() {
        viewModelScope.launch {
            _recipeUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            fetchFavoriteUseCase()
                .collect { list ->
                    _recipeUiState.update {
                        it.copy(
                            recipeList = list,
                            isLoading = false,
                        )
                    }
                }
        }
    }

    fun deleteDish(id: Long){
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}