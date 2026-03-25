package uk.ac.tees.mad.caloriedish.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    @Insert
    suspend fun insertSearch(search: RecentSearchEntity)

    @Query("SELECT * FROM recent_search ORDER BY id DESC LIMIT 5")
    fun getRecentSearches(): Flow<List<RecentSearchEntity>>

    @Query("DELETE FROM recent_search")
    suspend fun deleteSearch()

    @Query("""
        DELETE FROM recent_search 
        WHERE id NOT IN (
            SELECT id FROM recent_search 
            ORDER BY id DESC 
            LIMIT 5
        )
    """)
    suspend fun keepOnlyFive()
}

@Dao
interface FavoriteDishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: FavoriteDishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishList(dish: List<FavoriteDishEntity>)
    @Query("SELECT * FROM favorite_dishes")
    fun getFavoriteDishes(): Flow<List<FavoriteDishEntity>>
    @Query("DELETE FROM favorite_dishes WHERE id = :dishId")
    suspend fun deleteDish(dishId: Long)

    @Query("DELETE FROM favorite_dishes")
    suspend fun deleteAll()
}
