package uk.ac.tees.mad.caloriedish.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.caloriedish.data.model.NutritionResponse


interface DishApiService {

    @GET("api/nutrition-data")
    suspend fun getNutritionData(

        @Query("app_id")
        appId: String,

        @Query("app_key")
        appKey: String,

        @Query("nutrition-type")
        nutritionType: String = "cooking",

        @Query("ingr")
        ingredient: String

    ): NutritionResponse
}