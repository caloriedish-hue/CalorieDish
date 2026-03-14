package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.domain.repository.AuthRepository

class LoginUseCase(private  val authRepository: AuthRepository) {
    suspend operator fun invoke(email : String , password : String) = authRepository.loginUser(email ,password)
}