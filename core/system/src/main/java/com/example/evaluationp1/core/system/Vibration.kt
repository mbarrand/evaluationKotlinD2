package com.example.evaluationp1.core.system

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/***
 * Fonction qui joue la vibration
 */
@SuppressLint("MissingPermission")
fun Context.vibrate(
    millis: Long = 100L,
    // -1 corresponds to VibrationEffect.DEFAULT_AMPLITUDE
    amplitude: Int = -1
) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vm = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vm?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    } ?: return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(millis, amplitude))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(millis)
    }
}
