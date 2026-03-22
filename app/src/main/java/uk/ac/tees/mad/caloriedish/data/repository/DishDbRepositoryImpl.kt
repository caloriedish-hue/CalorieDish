package uk.ac.tees.mad.caloriedish.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishDao
import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishEntity
import uk.ac.tees.mad.caloriedish.data.local.RecentSearchDao
import uk.ac.tees.mad.caloriedish.data.local.RecentSearchEntity
import uk.ac.tees.mad.caloriedish.domain.repository.DishDbRepository

class DishDbRepositoryImpl(
    private val recentSearchDao: RecentSearchDao,
    private val favoriteDishDao: FavoriteDishDao
) : DishDbRepository {
    override suspend fun insertRecentSearch(recentSearch: RecentSearchEntity) {
        recentSearchDao.insertSearch(recentSearch)
        recentSearchDao.keepOnlyFive()
    }

    override  fun getRecentSearches(): Flow<List<RecentSearchEntity>> {
        return recentSearchDao.getRecentSearches()
    }

    override suspend fun getFavoriteDishes(): Flow<List<FavoriteDishEntity>> {
        return favoriteDishDao.getFavoriteDishes()
    }

    override suspend fun insertFavoriteDish(favoriteDish: FavoriteDishEntity) {
        favoriteDishDao.insertDish(favoriteDish)
    }

    override suspend fun deleteFavoriteDish(id: Long) {
        favoriteDishDao.deleteDish(id)
    }


    override suspend fun deleteAllSearches() {
        recentSearchDao.deleteSearch()
    }
}