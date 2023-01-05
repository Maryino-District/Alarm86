package com.example.alarm86.data

import android.util.Log
import com.example.alarm86.ui.AlarmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlarmRepository(private val alarmDao: AlarmDao) {
    private val ALARM_REPO_TAG = "ALARM_REPO_TAG"
    suspend fun addAlarm(alarm: AlarmModel) : Long {
        val alarmEntity = AlarmEntity(
            minute = alarm.minute,
            hour = alarm.hour,
            isEnabled = alarm.isEnabledAlarm
        )
         val result = alarmDao.addAlarm(alarmEntity)
        Log.d(ALARM_REPO_TAG, result.toString())
        return result
    }

    fun getAlarms() : Flow<List<AlarmModel>> {
        val alarmFlow = alarmDao.getAlarm()
    return alarmFlow.map{ it.map{item -> AlarmModel(minute = item.minute, hour = item.hour, isEnabledAlarm = item.isEnabled) }}
    }
}