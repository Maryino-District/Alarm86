package com.example.alarm86.infrastructure

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.example.alarm86.R
import com.example.alarm86.ui.MainActivity
import com.google.firebase.messaging.RemoteMessage

class FirebaseNotifications(private val context: Context) {

    val soundUri by lazy {
        Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName + "/" + R.raw.oleg)
    }
    fun showNotification(extraNotification: RemoteMessage.Notification) {
        val pendingIntent =  Intent(context, MainActivity::class.java).let {
            PendingIntent.getActivity(context, FIREBASE_NOTIFICATION_PENDING_INTENT_REQUEST_CODE, it, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = NotificationCompat.Builder(context, FIREBASE_NOTIFICATION_CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ararat)
            .setContentTitle(extraNotification.title)
            .setContentText(extraNotification.body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setSound(soundUri)
            .build()
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(FIREBASE_NOTIFICATION_ID, notification)

    }

    fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            FIREBASE_NOTIFICATION_CHANNEL_ID,
            "this.getString(R.string.firebase_channel_name)",
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = "getString(R.string.firebase_channel_description)"
        }
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
        notificationChannel.setSound(soundUri, audioAttributes)

        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}