package uk.ac.tees.mad.caloriedish.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

fun FoodNutrition.toJson(): String {
    return Json.encodeToString(this)
}

fun String.toFoodNutrition(): FoodNutrition {
    return Json.decodeFromString(this)
}