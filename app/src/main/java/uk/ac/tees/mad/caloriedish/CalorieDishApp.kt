package uk.ac.tees.mad.caloriedish

import android.app.Application
import uk.ac.tees.mad.caloriedish.utils.DependencyContainer

class CalorieDishApp : Application() {
    lateinit var dependencyContainer: DependencyContainer

    override fun onCreate() {
        super.onCreate()
        dependencyContainer = DependencyContainer(this)
    }

}

/**
 * application class for dependency creation
 */