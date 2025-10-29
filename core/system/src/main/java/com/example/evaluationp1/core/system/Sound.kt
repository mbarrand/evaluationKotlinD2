package com.example.evaluationp1.core.system

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

/***
 * Fonction qui joue le son du ace valorant (dans raw)
 */
fun Context.playDefault(@RawRes sound: Int) {
    val aceSound = MediaPlayer.create(this, sound)

    aceSound.setOnCompletionListener {
        it.release()
    }

    aceSound.start()
}