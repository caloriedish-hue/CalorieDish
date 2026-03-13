package uk.ac.tees.mad.caloriedish.utils

import android.util.Patterns

fun String.isValid() : Boolean{
   return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}