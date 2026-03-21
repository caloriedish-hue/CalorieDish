package uk.ac.tees.mad.caloriedish.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NutritionResponse(

    @SerialName("uri")
    val uri: String,

    @SerialName("dietLabels")
    val dietLabels: List<String>,

    @SerialName("healthLabels")
    val healthLabels: List<String>,

    @SerialName("cautions")
    val cautions: List<String>,

    @SerialName("ingredients")
    val ingredients: List<Ingredient>

)

@Serializable
data class Ingredient(

    @SerialName("text")
    val text: String,

    @SerialName("parsed")
    val parsed: List<ParsedIngredient>

)


@Serializable

data class ParsedIngredient(

    @SerialName("quantity")
    val quantity: Double,

    @SerialName("measure")
    val measure: String,

    @SerialName("foodMatch")
    val foodMatch: String,

    @SerialName("food")
    val food: String,

    @SerialName("foodId")
    val foodId: String,

    @SerialName("weight")
    val weight: Double,

    @SerialName("retainedWeight")
    val retainedWeight: Double,

    @SerialName("nutrients")
    val nutrients: Nutrients,

    @SerialName("status")
    val status: String
)


@Serializable

data class Nutrients(

    @SerialName("ENERC_KCAL")
    val energy: NutrientDetail,

    @SerialName("FAT")
    val fat: NutrientDetail,

    @SerialName("FASAT")
    val saturatedFat: NutrientDetail,

    @SerialName("CHOCDF")
    val carbs: NutrientDetail,

    @SerialName("PROCNT")
    val protein: NutrientDetail,

    @SerialName("FIBTG")
    val fiber: NutrientDetail,

    @SerialName("SUGAR")
    val sugar: NutrientDetail
)

@Serializable
data class NutrientDetail(

    @SerialName("label")
    val label: String,

    @SerialName("quantity")
    val quantity: Double,

    @SerialName("unit")
    val unit: String
)