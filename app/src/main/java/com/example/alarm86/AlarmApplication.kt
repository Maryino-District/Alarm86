package com.example.alarm86

import android.app.Application
import com.example.alarm86.infrastructure.FirebaseNotifications
import com.example.alarm86.infrastructure.NotificationMessagingService

class AlarmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseNotifications(context = this).createNotificationChannel()
    }
}