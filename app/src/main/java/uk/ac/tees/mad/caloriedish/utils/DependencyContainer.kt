package uk.ac.tees.mad.caloriedish.utils

import android.content.Context
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import uk.ac.tees.mad.caloriedish.data.local.DishDataBase
import uk.ac.tees.mad.caloriedish.data.remote.DishApiService
import uk.ac.tees.mad.caloriedish.data.remote.FirebaseDataSource
import uk.ac.tees.mad.caloriedish.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.caloriedish.data.repository.DishDbRepositoryImpl
import uk.ac.tees.mad.caloriedish.data.repository.DishRepositoryImpl
import uk.ac.tees.mad.caloriedish.domain.usecase.DeleteFavoriteUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.DeleteOnLogoutUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.DeleteSearchesUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.FetchDishUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.FetchFavoriteUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.FetchRecentUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.InsertFavoriteUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.InsertRecentUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.LoadOnLoginUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.LoginUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.LogoutUseCase
import uk.ac.tees.mad.caloriedish.domain.usecase.RegisterUseCase

class DependencyContainer (context: Context){
    val preferenceManager by lazy {
        PreferenceManager(context)
    }

    val db by lazy{
        Room.databaseBuilder(
            context,
            DishDataBase::class.java,
            "dish_db"
        ).build()
    }
    val authRepository by lazy {
        AuthRepositoryImpl(
            firebaseAuth = FirebaseAuth.getInstance(),
            firebaseFirestore = Firebase.firestore
        )
    }

    val loginUseCase by lazy {
        LoginUseCase(authRepository)
    }

    val registerUseCase by lazy {
        RegisterUseCase(authRepository)
    }

    val logoutUseCase by lazy {
        LogoutUseCase(authRepository)
    }

    private val BASE_URL = "https://api.edamam.com/"
    private val json = Json {
        ignoreUnknownKeys = true
    }


    val api: DishApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
            .create(DishApiService::class.java)
    }

    val dishRepository by lazy {
        DishRepositoryImpl(
            dishApiService = api,
            dishDao = db.favoriteDishDao(),
            recentSearchDao = db.recentSearchDao(),
            firebaseDataSource = firebaseDataSource
        )
    }

    val fetchDishUseCase by lazy {
        FetchDishUseCase(dishRepository)
    }

    val firebaseDataSource by lazy { 
        FirebaseDataSource(
            firebaseAuth = FirebaseAuth.getInstance(),
            fireStore = FirebaseFirestore.getInstance()
        )
    }

    val dishDbRepository by lazy {
        DishDbRepositoryImpl(
            db.recentSearchDao(),
            db.favoriteDishDao(),
            firebaseDataSource = firebaseDataSource
        )
    }

    val recentSearchUseCase by lazy {
        InsertRecentUseCase(dishDbRepository)
    }

    val fetchRecentUseCase by lazy {
        FetchRecentUseCase(dishDbRepository)
    }

    val insertFavoriteUseCase by lazy {
      InsertFavoriteUseCase(dishDbRepository)
    }
    val fetchFavoriteUseCase by lazy {
        FetchFavoriteUseCase(dishDbRepository)
    }
    val deleteAllSearches by lazy {
        DeleteSearchesUseCase(dishDbRepository)
    }

    val deleteFavoriteUseCase by lazy {
        DeleteFavoriteUseCase(dishDbRepository)
    }

    val loadOnLoginUseCase by lazy {
        LoadOnLoginUseCase(dishRepository)
    }

    val deleteOnLogoutUseCase by lazy {
        DeleteOnLogoutUseCase(dishRepository)
    }
}