package uk.ac.tees.mad.caloriedish.utils

import android.content.Context

class DependencyContainer (context: Context){


    val preferenceManager by lazy {
        PreferenceManager(context)
    }


}