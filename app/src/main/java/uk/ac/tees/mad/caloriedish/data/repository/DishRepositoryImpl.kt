package uk.ac.tees.mad.caloriedish.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishDao
import uk.ac.tees.mad.caloriedish.data.local.RecentSearchDao
import uk.ac.tees.mad.caloriedish.data.remote.DishApiService
import uk.ac.tees.mad.caloriedish.data.remote.FirebaseDataSource
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.domain.repository.DishRepository
import uk.ac.tees.mad.caloriedish.utils.toFavoriteDishEntity
import uk.ac.tees.mad.caloriedish.utils.toFoodNutrition

class DishRepositoryImpl (
    private val dishApiService: DishApiService ,
    private val dishDao: FavoriteDishDao ,
    private val recentSearchDao: RecentSearchDao ,
    private val firebaseDataSource: FirebaseDataSource
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

    override suspend fun loadOnLogin() {
        firebaseDataSource.fetchFavorites { list ->
            CoroutineScope(Dispatchers.IO).launch {
                list.forEach {
                    dishDao.insertDish(it.toFavoriteDishEntity())
                }
            }
        }
    }

    override suspend fun deleteOnLogout() {
        dishDao.deleteAll()
        recentSearchDao.deleteSearch()
    }

}