package uk.ac.tees.mad.caloriedish.utils

import uk.ac.tees.mad.caloriedish.data.model.NutritionResponse
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.domain.model.Nutrient

fun NutritionResponse.toFoodNutrition(): FoodNutrition {

    val parsed = ingredients.first().parsed.first()
    val nutrients = parsed.nutrients

    return FoodNutrition(
        foodName = parsed.food,
        measure = parsed.measure ?:"",
        weight = parsed.weight,

        calories = Nutrient(
            nutrients.energy.quantity,
            nutrients.energy.unit
        ),

        protein = Nutrient(
            nutrients.protein.quantity,
            nutrients.protein.unit
        ),

        carbs = Nutrient(
            nutrients.carbs.quantity,
            nutrients.carbs.unit
        ),

        fat = Nutrient(
            nutrients.fat.quantity,
            nutrients.fat.unit
        ),

        fiber = Nutrient(
            nutrients.fiber.quantity,
            nutrients.fiber.unit
        ),

        sugar = Nutrient(
            nutrients.sugar.quantity,
            nutrients.sugar.unit
        )
    )
}