package com.example.alarm86.ui

data class AlarmModel(
     val hour: Int,
     val minute: Int,
     val isEnabledAlarm: Boolean = false
) {
}
