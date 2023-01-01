package com.example.alarm86.ui

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alarm86.data.AlarmDatabase
import com.example.alarm86.data.AlarmRepository
import com.example.alarm86.infrastrucrure.AlarmBroadcastReceiver
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: AlarmRepository? = null
    private val _data = MutableLiveData<List<AlarmModel>>(emptyList())
    private var job: Job? = null
    val data: LiveData<List<AlarmModel>> = _data
    init {
        repository = AlarmRepository(AlarmDatabase.getDatabase(application.applicationContext).alarmDao())
        loadData()

    }

    fun loadData() {
        job = viewModelScope.launch {
            repository?.let{(it.getAlarms()).collect {
                _data.postValue(it)
            }}
        }
    }

    fun addAlarm(alarm: AlarmModel) {
        viewModelScope.launch {
            repository?.addAlarm(alarm)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun scheduleAlarm(hour: Int, minutes: Int) {
        val pendingIntent = buildAlarmPendingIntend()
        val alarmCalendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minutes)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH)+1)
        }
        (getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as? AlarmManager)?.apply {
            setRepeating(AlarmManager.RTC_WAKEUP, alarmCalendar.timeInMillis, 86400000, pendingIntent)
        }

    }

    private fun buildAlarmPendingIntend() = Intent(getApplication(), AlarmBroadcastReceiver::class.java).let {
                PendingIntent.getBroadcast(getApplication(),68, it, PendingIntent.FLAG_IMMUTABLE)

    }
}