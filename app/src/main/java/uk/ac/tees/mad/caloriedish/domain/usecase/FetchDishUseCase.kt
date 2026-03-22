package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.domain.repository.DishRepository

class FetchDishUseCase(private val dishRepository: DishRepository){
    suspend operator fun invoke(ingredient: String): Result<FoodNutrition> {
        return dishRepository.fetchDish(ingredient)
    }
}