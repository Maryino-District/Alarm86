package com.example.alarm86.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlarmEntity::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
    companion object {
        @Volatile
        private var INSTANCE:AlarmDatabase? = null
        fun getDatabase(context: Context):AlarmDatabase{
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AlarmDatabase::class.java,
                    "alarmdatabase"
                ).build()

                return INSTANCE!!
            }
        }
    }
}
