package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Result<Unit> {
        return authRepository.logout()
    }
}