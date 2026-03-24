package uk.ac.tees.mad.caloriedish.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition


class DetailViewModel (application: Application) :
    AndroidViewModel(application) {
        private val _detailUiState = MutableStateFlow(DetailUiState())
        val detailUiState = _detailUiState.asStateFlow()

        fun setFoodNutrition(foodNutrition: FoodNutrition){
            _detailUiState.update {
                it.copy(
                    foodNutrition = foodNutrition
                )
            }
        }

    }