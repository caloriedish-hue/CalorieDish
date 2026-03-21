package uk.ac.tees.mad.caloriedish.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodNutrition(
    val foodName: String,
    val measure: String,
    val weight: Double,
    val weightUnit: String = "g",
    val calories: Nutrient,
    val protein: Nutrient,
    val carbs: Nutrient,
    val fat: Nutrient,
    val fiber: Nutrient,
    val sugar: Nutrient
)

@Serializable
data class Nutrient(
    val value: Double,
    val unit: String
)