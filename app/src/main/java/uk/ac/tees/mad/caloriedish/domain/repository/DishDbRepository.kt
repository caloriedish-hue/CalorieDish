package uk.ac.tees.mad.caloriedish.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishEntity
import uk.ac.tees.mad.caloriedish.data.local.RecentSearchEntity

interface DishDbRepository {
    suspend fun insertRecentSearch(recentSearch: RecentSearchEntity)
    fun getRecentSearches(): Flow<List<RecentSearchEntity>>
    suspend fun getFavoriteDishes(): Flow<List<FavoriteDishEntity>>
    suspend fun insertFavoriteDish(favoriteDish: FavoriteDishEntity)

    suspend fun deleteFavoriteDish(id: Long)

    suspend fun deleteAllSearches()
}