package com.example.alarm86.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarm: AlarmEntity) : Long

    @Query("SELECT * FROM AlarmEntity")
    fun getAlarm() : Flow<List<AlarmEntity>>

}