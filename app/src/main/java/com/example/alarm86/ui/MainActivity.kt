package com.example.alarm86.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import com.example.alarm86.R
import com.example.alarm86.ui.fragments.AlarmManagerFragment

class MainActivity : AppCompatActivity() {
    private val MAIN_ACTIVITY_TAG = "Main_activity_tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            add(R.id.fragment_container, AlarmManagerFragment())
            setReorderingAllowed(true)
        }
        Log.d(MAIN_ACTIVITY_TAG, "MA: On create")
    }
}