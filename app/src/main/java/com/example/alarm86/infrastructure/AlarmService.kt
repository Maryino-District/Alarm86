package com.example.alarm86.infrastructure

import android.app.*
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.os.Vibrator
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarm86.R
import com.example.alarm86.ui.MainActivity
import java.util.*

class AlarmService : Service() {
  /*  private  val soundUri by lazy { by lazy {
        Log.d(ALARM_SERVICE_TAG, "Service: Init sounduris")
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.cure)
            2 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.hova)
            3 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.metronome)
            4 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.money)
            5 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.numb)
            6 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.pieces)
            7 -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.versus)
            else -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.versus)
        }
    }*/
  val soundUri by lazy {
      Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + R.raw.sample_free)
  }

    private val vibration = lazy {
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        Log.d(ALARM_SERVICE_TAG, "Service: On Start Command")
        val pattern = longArrayOf(0, 100, 1000)
        vibration.value.vibrate(pattern, 0)
        startForeground(FOREGROUND_SERVICE_ID, buildNotification())
        return START_STICKY


    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            this.getString(R.string.notificaiton_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description = resources.getString(R.string.notification_channel_descriprion)
        notificationChannel.enableLights(true)
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
        notificationChannel.setSound(soundUri, audioAttributes)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun buildNotification() : Notification {

        Log.d(ALARM_SERVICE_TAG, "Service: Build notification")
        val pendingIntent = Intent(this, MainActivity::class.java).let {
            it.action = START_ACTIVITY_WITH_ALARM_ACTION
            PendingIntent.getActivity(this, 68, it, PendingIntent.FLAG_IMMUTABLE)
        }
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(resources.getString(R.string.notification_alarm_title))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setFullScreenIntent(pendingIntent, true)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setSound(soundUri)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
       // mediaPlayer.stop()
        vibration.value.cancel()

    }
}

