package uk.ac.tees.mad.caloriedish.utils

import kotlinx.serialization.StringFormat
import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishEntity
import uk.ac.tees.mad.caloriedish.data.local.RecentSearchEntity
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

fun String.toRecentSearchEntity() = RecentSearchEntity(query = this)
fun FoodNutrition.toFavoriteDishEntity() = FavoriteDishEntity(nutrition = this)

fun FavoriteDishEntity.toFoodNutrition(): FoodNutrition{
    return FoodNutrition(
        foodName = this.nutrition.foodName,
        measure = this.nutrition.measure,
        weight = this.nutrition.weight,
        weightUnit = this.nutrition.weightUnit,
        calories = this.nutrition.calories,
        protein = this.nutrition.protein,
        carbs = this.nutrition.carbs,
        fat = this.nutrition.fat,
        fiber = this.nutrition.fiber,
        sugar = this.nutrition.sugar
    )
}