package uk.ac.tees.mad.caloriedish.domain.repository

interface AuthRepository {
   suspend fun loginUser(email : String , password : String) : Result<Unit>
   suspend fun registerUser(email : String , password : String) : Result<Unit>
    fun logout() : Result<Unit>
}