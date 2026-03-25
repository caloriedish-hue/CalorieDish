package uk.ac.tees.mad.caloriedish.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uk.ac.tees.mad.caloriedish.domain.model.FoodNutrition
import uk.ac.tees.mad.caloriedish.utils.TextToSpeechManager


class DetailViewModel(application: Application) :
    AndroidViewModel(application) {
    private val _detailUiState = MutableStateFlow(DetailUiState())

    private var tts = TextToSpeechManager(application){
        _detailUiState.update {
            it.copy(
                isSpeaking = false
            )
        }
    }
    val detailUiState = _detailUiState.asStateFlow()

    fun setFoodNutrition(foodNutrition: FoodNutrition) {
        _detailUiState.update {
            it.copy(
                foodNutrition = foodNutrition
            )
        }
    }

    fun onSpeechToggle() {

        val state = _detailUiState.value
        val food = state.foodNutrition?:return

        if(state.isSpeaking){
            tts.stop()
            _detailUiState.update {
                it.copy(
                    isSpeaking = false
                )
            }
        }else{
            val speechText = """
                ${food.foodName}.
                Serving ${food.measure} ${food.weight} ${food.weightUnit}.
                Calories ${food.calories.value.pretty()}.
                Protein ${food.protein.value.pretty()} grams.
                Carbs ${food.carbs.value.pretty()} grams.
                Fat ${food.fat.value.pretty()} grams.
                Fiber ${food.fiber.value.pretty()} grams.
                Sugar ${food.sugar.value.pretty()} grams.
            """.trimIndent()
            tts.speak(speechText)
            _detailUiState.update {
                it.copy(
                    isSpeaking = true
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        tts.shutDown()
    }

}



fun Double.pretty(): String = String.format("%.2f", this)