package uk.ac.tees.mad.caloriedish.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.domain.model.Nutrient

class Converters {

    private val gson = Gson()

    // FoodNutrition -> String
    @TypeConverter
    fun fromFoodNutrition(nutrition: FoodNutrition): String {
        return gson.toJson(nutrition)
    }

    // String -> FoodNutrition
    @TypeConverter
    fun toFoodNutrition(data: String): FoodNutrition {
        return gson.fromJson(data, FoodNutrition::class.java)
    }

    // Nutrient -> String
    @TypeConverter
    fun fromNutrient(nutrient: Nutrient): String {
        return gson.toJson(nutrient)
    }

    // String -> Nutrient
    @TypeConverter
    fun toNutrient(data: String): Nutrient {
        return gson.fromJson(data, Nutrient::class.java)
    }
}