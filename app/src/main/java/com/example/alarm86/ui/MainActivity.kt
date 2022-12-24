package com.example.alarm86.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.alarm86.R
import com.example.alarm86.databinding.ActivityMainBinding
import com.example.alarm86.ui.fragments.AlarmManagerFragment

class MainActivity : AppCompatActivity() {
    private val MAIN_ACTIVITY_TAG = "Main_activity_tag"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(MAIN_ACTIVITY_TAG, "MA: On create")
    }
}