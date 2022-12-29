package com.example.alarm86.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alarm86.ui.AlarmModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarm: AlarmEntity) : Long

    @Query("SELECT * FROM AlarmEntity")
    fun getAlarmList() : Flow<List<AlarmEntity>>

}