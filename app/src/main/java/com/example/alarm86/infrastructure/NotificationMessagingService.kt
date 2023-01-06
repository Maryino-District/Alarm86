package com.example.alarm86.infrastructure

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationMessagingService : FirebaseMessagingService() {


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        FirebaseNotifications(this).createNotificationChannel()
        message.notification?.let {
            FirebaseNotifications(this).showNotification(it)
        }
    }

}