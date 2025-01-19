package com.petsimulator.utils

import android.content.Context
import android.media.MediaPlayer

//Глобальный MediaPlayer
private var mediaPlayer: MediaPlayer? = null

fun playSound(context: Context, soundResId: Int, playOnce: Boolean = true) {
    //Остановить текущий звук, если проигрывается
    stopSound()

    //Создать новый экземпляр и запустить
    mediaPlayer = MediaPlayer.create(context, soundResId).apply {
        if (playOnce) {
            setOnCompletionListener {
                it.release()  //Освобождаем ресурсы после завершения
                mediaPlayer = null
            }
        }
        else {
            setOnCompletionListener {
                it.seekTo(0) //Перемещает воспроизведение в начало
                it.start()   //Запускает звук снова
            }
        }
        start()
    }
}

fun stopSound() {
    mediaPlayer?.let {
        if (it.isPlaying) {
            it.stop()
        }
        it.release()
        mediaPlayer = null
    }
}
