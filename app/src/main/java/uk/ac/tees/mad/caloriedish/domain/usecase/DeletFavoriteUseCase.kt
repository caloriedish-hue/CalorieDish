package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.repository.DishDbRepository

class DeleteFavoriteUseCase(
    private val dishDbRepository: DishDbRepository
) {
    suspend operator fun invoke(id: Long) {
        dishDbRepository.deleteFavoriteDish(id)
    }
}