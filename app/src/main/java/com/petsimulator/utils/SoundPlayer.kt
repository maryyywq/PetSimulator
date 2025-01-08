package com.petsimulator.utils

import android.content.Context
import android.media.MediaPlayer

//Глобальный MediaPlayer
private var mediaPlayer: MediaPlayer? = null

fun playSound(context: Context, soundResId: Int) {
    //Остановить текущий звук, если проигрывается
    stopSound()

    //Создать новый экземпляр и запустить
    mediaPlayer = MediaPlayer.create(context, soundResId).apply {
        setOnCompletionListener {
            it.release()  //Освобождаем ресурсы после завершения
            mediaPlayer = null
        }
        start()
    }
}

fun stopSound() {
    mediaPlayer?.apply {
        stop()
        release()
    }
}