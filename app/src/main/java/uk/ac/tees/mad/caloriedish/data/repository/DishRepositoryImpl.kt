package uk.ac.tees.mad.caloriedish.data.repository

import android.util.Log
import uk.ac.tees.mad.caloriedish.data.remote.DishApiService
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.domain.repository.DishRepository
import uk.ac.tees.mad.caloriedish.utils.toFoodNutrition

class DishRepositoryImpl (
    private val dishApiService: DishApiService
): DishRepository {
    override suspend fun fetchDish(ingredient: String):Result<FoodNutrition> {
        return try {

            val response = dishApiService.getNutritionData(
                appId = "f20740f8",
                appKey = "492fefb24b6e1d2ff3ac4bc3ab5e8b87",
                ingredient = ingredient
            )
            Log.d("Dish" , response.toString())
            Result.success(response.toFoodNutrition())

        } catch (e: Exception) {
            Log.d("Dish" , e.message.toString())
            Result.failure(e)
        }
    }
}