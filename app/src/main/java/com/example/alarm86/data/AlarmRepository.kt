package com.example.alarm86.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.alarm86.ui.AlarmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class AlarmRepository(private val alarmDao: AlarmDao) {
    private val ALARM_REPO_TAG = "ALARM_REPO_TAG"
    suspend fun addAlarm(alarm: AlarmModel) : Long {
        val alarmEntity: AlarmEntity = AlarmEntity(
            minute = alarm.minute!!,
            hour = alarm.hour!!,
            isEnabled = alarm.isEnabled
        )
         val result = alarmDao.addAlarm(alarmEntity)
        Log.d(ALARM_REPO_TAG, result.toString())
        return result
    }

    suspend fun getAlarms() : Flow<List<AlarmModel>> {
        val alarmListFlow = alarmDao.getAlarmList()

     //   val alarmList = alarmListFlow.toList()
    return alarmListFlow.map{list-> list.map { item -> AlarmModel(minute = item.minute, hour = item.hour, isEnabled = item.isEnabled) }}
       // return alarmListFlow
    }
}