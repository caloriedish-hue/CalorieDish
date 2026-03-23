package uk.ac.tees.mad.caloriedish.presentation.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.ui.graphics.vector.ImageVector
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition

data class DetailUiState(
    val foodNutrition: FoodNutrition ? = null ,
    val isSpeaking : Boolean = false ,
){
    val icon : ImageVector
        get() = when{
            isSpeaking -> Icons.AutoMirrored.Filled.VolumeUp
            else -> Icons.AutoMirrored.Filled.VolumeOff
        }
}