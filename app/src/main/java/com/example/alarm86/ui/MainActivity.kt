package com.example.alarm86.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.alarm86.R
import com.example.alarm86.ui.fragments.AlarmManagerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            add(R.id.fragment_container, AlarmManagerFragment())
            setReorderingAllowed(true)
        }
    }
}