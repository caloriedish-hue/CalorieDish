package uk.ac.tees.mad.caloriedish.utils

import android.content.Context
import androidx.core.content.edit


class PreferenceManager (context: Context){

    companion object{
        const val MY_PREFS = "MyPrefs"
        const val IS_LOGGED_IN = "IsLoggedIn"
    }
    private val sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)

    fun getLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(IS_LOGGED_IN , false)
    }

    fun setLoggedIn(isLoggedIn: Boolean){
        sharedPreferences.edit{
            putBoolean(IS_LOGGED_IN, isLoggedIn)
        }
    }



}