package com.example.alarm86.infrastructure

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarm86.R
import com.example.alarm86.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotificationChannel()
        message.notification?.let {
            showNotification(it)
        }
    }

    private fun showNotification(extraNotification: RemoteMessage.Notification) {
        val pendingIntent =  Intent(this, MainActivity::class.java).let {
            PendingIntent.getActivity(this, FIREBASE_NOTIFICATION_PENDING_INTENT_REQUEST_CODE, it, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = NotificationCompat.Builder(this, FIREBASE_NOTIFICATION_CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.ararat)
            .setContentTitle(extraNotification.title)
            .setContentText(extraNotification.body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
           //.setSound(Uri.parse("android.resource://com.example.alarm89/raw/oleg"))
            .build()
        NotificationManagerCompat.from(this).notify(FIREBASE_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            FIREBASE_NOTIFICATION_CHANNEL_ID,
            this.getString(R.string.firebase_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT,
        ).apply {
            description = getString(R.string.firebase_channel_description)
        }
        NotificationManagerCompat.from(this).createNotificationChannel(notificationChannel)
    }
}