package com.example.alarm86.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val minute: Int,
    val hour: Int,
    val isEnabled: Boolean
)
