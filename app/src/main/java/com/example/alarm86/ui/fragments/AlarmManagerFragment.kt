package com.example.alarm86.ui.fragments

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarm86.databinding.FragmentAlarmManagerBinding

class AlarmManagerFragment : Fragment() {

private lateinit var binding: FragmentAlarmManagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlarmManagerBinding.inflate(inflater)
        return binding.root
    }
}