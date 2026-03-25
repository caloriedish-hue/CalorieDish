package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.repository.DishRepository

class DeleteOnLogoutUseCase(private val dishRepository: DishRepository) {
    suspend operator fun invoke() {
        dishRepository.deleteOnLogout()
    }
}