package com.example.alarm86.ui.fragments

import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarm86.databinding.FragmentAlarmManagerBinding

class AlarmManagerFragment : Fragment() {

    private val ALARM_MANAGER_FRAGMENT_TAG = "Alarm_manager_tag"
    private lateinit var binding: FragmentAlarmManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(ALARM_MANAGER_FRAGMENT_TAG, "AMF: onCreateView")
        binding = FragmentAlarmManagerBinding.inflate(inflater)
        return binding.root
    }
}