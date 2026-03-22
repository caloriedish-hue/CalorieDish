package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.data.local.FavoriteDishEntity
import uk.ac.tees.mad.caloriedish.domain.repository.DishDbRepository

class InsertFavoriteUseCase (private val repository: DishDbRepository) {
    suspend operator fun invoke(favoriteDish: FavoriteDishEntity){
        repository.insertFavoriteDish(favoriteDish)
    }
}