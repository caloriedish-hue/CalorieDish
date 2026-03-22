package uk.ac.tees.mad.caloriedish.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

@Entity(tableName = "favorite_dishes")
data class FavoriteDishEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    val nutrition: FoodNutrition ,
)



@Entity(tableName = "recent_search")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    val query : String ,
    val timestamp : Long = System.currentTimeMillis()
)
