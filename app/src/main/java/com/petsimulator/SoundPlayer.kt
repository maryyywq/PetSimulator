package com.petsimulator

import android.content.Context
import android.media.MediaPlayer

//Глобальный MediaPlayer
private var mediaPlayer: MediaPlayer? = null

fun playSound(context: Context, soundResId: Int) {
    //Остановить текущий звук, если проигрывается
    mediaPlayer?.apply {
        stop()
        release()
    }

    //Создать новый экземпляр и запустить
    mediaPlayer = MediaPlayer.create(context, soundResId).apply {
        setOnCompletionListener {
            it.release()  //Освобождаем ресурсы после завершения
            mediaPlayer = null
        }
        start()
    }
}