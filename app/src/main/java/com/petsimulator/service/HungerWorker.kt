package com.petsimulator.service

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.petsimulator.R
import com.petsimulator.converters.toEntity
import com.petsimulator.converters.toPet
import com.petsimulator.database.AppDatabase
import com.petsimulator.utils.AppState
import com.petsimulator.utils.NotificationUtil

class HungerWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        if (AppState.isAppActive) {
            return Result.retry() //Перезапускаем задачу позже
        }

        return try {
            val database = AppDatabase.getInstance(applicationContext)
            val pet = database.petDao().getPet()?.toPet() ?: throw Exception("Ошибка обновления состояния питомца")

            try {
                pet.satiety -= 1
            }
            catch (e: IllegalArgumentException) {
                pet.satiety = 0
                sendHungerNotification()
            }
            database.petDao().updatePet(pet.toEntity()) //обновляем данные питомца в БД

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    private fun sendHungerNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(applicationContext, NotificationUtil.getChannelId())
            .setSmallIcon(R.drawable.hunger_icon)
            .setContentTitle("Ваш питомец голоден!")
            .setContentText("Сытость питомца упала до нуля. Не забудьте его покормить!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}
