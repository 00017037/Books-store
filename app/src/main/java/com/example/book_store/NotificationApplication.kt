package com.example.book_store

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

class NotificationApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            "request_status",
            "Request_Status",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description= "Successfully added"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE)as NotificationManager;
        notificationManager.createNotificationChannel(notificationChannel)
    }

}