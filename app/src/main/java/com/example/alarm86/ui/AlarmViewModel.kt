package com.example.alarm86.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alarm86.data.AlarmDatabase
import com.example.alarm86.data.AlarmRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
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
}