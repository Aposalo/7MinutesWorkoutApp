package eu.tutorials.a7_minutesworkoutapp.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import eu.tutorials.a7_minutesworkoutapp.R
import java.lang.Exception

class SoundPlayerWorkout(applicationContext: Context) {

    private lateinit var player: MediaPlayer

    init {
        try {
            val soundURI  = Uri.parse(
                "android.resource://eu.tutorials.a7_minutesworkoutapp/" + R.raw.press_start
            )
            player = MediaPlayer.create(applicationContext,soundURI)

        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun start() {
        player.isLooping = false
        player.start()
    }

    fun stop() {
        player.stop()
    }

}