package uk.ac.tees.mad.caloriedish.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.caloriedish.CalorieDishApp
import uk.ac.tees.mad.caloriedish.domain.usecase.DeleteSearchesUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.FetchDishUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.FetchRecentUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.InsertFavoriteUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.InsertRecentUseCase
import uk.ac.tees.mad.caloriedish.utils.getSuggestions
import uk.ac.tees.mad.caloriedish.utils.toFavoriteDishEntity
import uk.ac.tees.mad.caloriedish.utils.toRecentSearchEntity

class HomeViewModel(application: Application) :
    AndroidViewModel(application) {

    private val fetchDishUseCase: FetchDishUseCase =
        (application as CalorieDishApp).dependencyContainer.fetchDishUseCase

    private val recentUseCase: InsertRecentUseCase =
        (application as CalorieDishApp).dependencyContainer.recentSearchUseCase

    private val fetchRecentUseCase: FetchRecentUseCase =
        (application as CalorieDishApp).dependencyContainer.fetchRecentUseCase

    private val insertFavoriteUseCase: InsertFavoriteUseCase =
        (application as CalorieDishApp).dependencyContainer.insertFavoriteUseCase

    private val deleteAllSearchesUseCase : DeleteSearchesUseCase =
        (application as CalorieDishApp).dependencyContainer.deleteAllSearches


    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()


    init {
        fetchRecentSearches()
    }


    fun onClearAllCLick(){
        viewModelScope.launch {
            deleteAllSearchesUseCase.invoke()
        }
    }

    fun onFavoriteClick(){
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    isLoading = true,
                )
            }
            val entity = _homeUiState.value.foodNutrition?.toFavoriteDishEntity()

            entity?.let {
                insertFavoriteUseCase
                    .invoke(
                        it
                    )
            }
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    isAddedToFavorite = true
                )
            }
        }
    }

    private fun fetchRecentSearches() {
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    isLoading = true,
                )
            }
            fetchRecentUseCase
                .invoke()
                .collect { list ->
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            recentSearch = list
                        )
                    }
                }
        }
    }


    fun onQueryChange(query: String) {
        _homeUiState.value = _homeUiState.value.copy(
            query = query,
            suggestions = getSuggestions(query),
        )
    }

    fun onSuggestionSelected(suggestion: String) {
        _homeUiState.update {
            it.copy(
                query = suggestion
            )
        }
    }

    fun onSearchIconClick(query: String){
            _homeUiState.update {
                it.copy(
                    query = query .removePrefix("1")
                )
            }
        onSearch()
    }



    fun onSearch() {
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    isLoading = true,
                )
            }
            val selectedQuery = "1" + _homeUiState.value.query
            fetchDishUseCase(selectedQuery)
                .onSuccess { success ->
                    recentUseCase.invoke(selectedQuery.toRecentSearchEntity())
                    _homeUiState.update {
                        it.copy(
                            foodNutrition = success,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
        }
    }
}