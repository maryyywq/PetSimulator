package com.petsimulator.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationUtil {
    private const val CHANNEL_ID = "pet_hunger_channel"
    private const val CHANNEL_NAME = "Pet Hunger Notifications"

    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications about pet hunger"
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun getChannelId(): String = CHANNEL_ID
}
