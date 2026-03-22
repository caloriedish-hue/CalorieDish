package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.repository.DishDbRepository

class FetchFavoriteUseCase(private val repository: DishDbRepository) {
    suspend operator fun invoke() = repository.getFavoriteDishes()
}