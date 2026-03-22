package uk.ac.tees.mad.caloriedish.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RecentSearchEntity::class,
        FavoriteDishEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DishDataBase : RoomDatabase(){
    abstract fun recentSearchDao(): RecentSearchDao
    abstract fun favoriteDishDao(): FavoriteDishDao
}