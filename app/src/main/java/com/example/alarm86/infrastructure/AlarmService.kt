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
  /*  private val mediaPlayer: MediaPlayer by lazy {
        Log.d(ALARM_SERVICE_TAG, "Service: Init media player")
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> MediaPlayer.create(this, R.raw.sample_one)
            2 -> MediaPlayer.create(this, R.raw.sample_to)
            3 -> MediaPlayer.create(this, R.raw.sample_free)
            4 -> MediaPlayer.create(this, R.raw.sample_one)
            5 -> MediaPlayer.create(this, R.raw.sample_to)
            6 -> MediaPlayer.create(this, R.raw.sample_free)
            7 -> MediaPlayer.create(this, R.raw.sample_one)
            else -> MediaPlayer.create(this, R.raw.sample_free)
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
        //mediaPlayer.start()
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

        NotificationManagerCompat.from(this).createNotificationChannel(notificationChannel)
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

