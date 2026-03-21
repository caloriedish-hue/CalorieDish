package uk.ac.tees.mad.caloriedish.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

class TextToSpeechManager(context : Context ,
    private val onSpeechCompleted:()-> Unit) : TextToSpeech.OnInitListener{

    private var tts :TextToSpeech = TextToSpeech(context , this)
    private var isReady = false

    override fun onInit(status: Int) {
       if(status == TextToSpeech.SUCCESS){
           tts.language = Locale.US

           tts.setOnUtteranceProgressListener(
               object : UtteranceProgressListener() {
                   override fun onStart(utteranceId: String?) {}
                   override fun onDone(utteranceId: String?) {
                       onSpeechCompleted()
                   }
                   override fun onError(utteranceId: String?) {
                       onSpeechCompleted()
                   }
               }
           )
           tts.setSpeechRate(0.9f)
           tts.setPitch(1.0f)
           isReady = true
       }
    }

    fun speak(text : String){
        if(!isReady) return
        tts.speak(
            text ,
            TextToSpeech.QUEUE_FLUSH ,
            null ,
            "nutrition_tts"
        )
    }

    fun stop(){
        tts.stop()
    }

    fun shutDown(){
        tts.shutdown()
    }
}