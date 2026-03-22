package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.repository.DishDbRepository

class FetchRecentUseCase(private val repository: DishDbRepository) {
    suspend operator fun invoke() = repository.getRecentSearches()
}