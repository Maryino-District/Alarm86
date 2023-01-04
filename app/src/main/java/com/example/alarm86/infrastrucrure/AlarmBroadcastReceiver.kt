package com.example.alarm86.infrastrucrure

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Calendar

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == p1?.action){
            Log.d(ALARM_BROADCAST_RECEIVER_TAG, "Alarm BR: onReceive, Boot completed, go reschedule")
            startRescheduleAlarmService(context = p0)
        } else {
            Log.d(ALARM_BROADCAST_RECEIVER_TAG, "Alarm BR: onReceive, go to startAlarmService")
            startAlarmService(intent = p1!!, context = p0)
        }
    }

    private fun startAlarmService(intent: Intent, context: Context?) {
        Intent(context, AlarmService::class.java).also {
            it.putExtra(DAY_OF_WEEK_SERVICE_KEY, Calendar.DAY_OF_WEEK)
            Log.d(ALARM_SERVICE_TAG, "AlarmService: startAlarmService, day is ${Calendar.DAY_OF_WEEK}")
            context?.startForegroundService(it)
        }
    }

    private fun startRescheduleAlarmService(context: Context?) {
        Intent(context, RecheduleAlarmWorker::class.java).also {
            TODO("Work manager")
        }
    }
}