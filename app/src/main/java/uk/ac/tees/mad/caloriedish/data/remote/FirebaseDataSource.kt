package uk.ac.tees.mad.caloriedish.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.utils.toFoodNutrition
import uk.ac.tees.mad.caloriedish.utils.toJson

class FirebaseDataSource(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) {

    companion object {
        const val USERS = "users"
    }

   private fun getUserId(): String {
        return firebaseAuth.currentUser?.uid ?: ""
    }

    fun saveFavoriteDish(
        nutrition: FoodNutrition
    ) {

        val data = mapOf(
            "nutrition" to nutrition.toJson()
        )

        fireStore.collection("users")
            .document(getUserId())
            .collection("savedFoods")
            .add(data)
    }

    fun fetchFavorites(
        onResult: (List<FoodNutrition>) -> Unit
    ) {
        fireStore.collection("users")
            .document(getUserId())
            .collection("savedFoods")
            .get()
            .addOnSuccessListener { result ->

                val foods = result.mapNotNull {

                    val json = it.getString("nutrition")
                    json?.toFoodNutrition()
                }
                onResult(foods)
            }
    }

}