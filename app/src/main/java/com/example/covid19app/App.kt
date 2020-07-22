package com.example.covid19app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationsChannel()
    }

    private fun createNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val ServiceChannel = NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(ServiceChannel)
        }
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "channel1"
        const val CHANNEL_ID = "MyChannelId"
    }
}