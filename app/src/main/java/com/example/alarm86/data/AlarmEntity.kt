package com.example.alarm86.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlarmEntity(
    @PrimaryKey
    val id: Int = 10,
    val minute: Int,
    val hour: Int,
    val isEnabled: Boolean,

)
