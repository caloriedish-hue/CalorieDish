package uk.ac.tees.mad.caloriedish.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.caloriedish.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun loginUser(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerUser(
        email: String,
        password: String
    ): Result<Unit> {

        return  try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()

            val uid = firebaseAuth.currentUser?.uid
                ?: return Result.failure(Exception("User not found"))

            firebaseFirestore
                .collection(USERS_COLLECTION)
                .document(uid)
                .set(
                    mapOf(
                        "email" to email,
                        "password" to password,
                        "uid" to uid,
                        "created_at" to System.currentTimeMillis()
                    )
                )
                .await()
            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun logout(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }
    companion object {
        private const val USERS_COLLECTION = "users"
    }
}