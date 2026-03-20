package uk.ac.tees.mad.caloriedish.domain.repository

import uk.ac.tees.mad.caloriedish.data.model.NutritionResponse
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

interface DishRepository {
    suspend fun fetchDish(ingredient : String): Result<FoodNutrition>
}