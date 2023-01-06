package com.example.alarm86.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.alarm86.R
import com.example.alarm86.databinding.FragmentAlarmEventBinding
import com.example.alarm86.infrastructure.AlarmService
import com.example.alarm86.ui.AlarmViewModel

class AlarmEventFragment : Fragment() {
    private val viewModel: AlarmViewModel by viewModels()
    private lateinit var binding: FragmentAlarmEventBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentAlarmEventBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            Intent(this.context, AlarmService::class.java).let {
                this.context?.stopService(it)
            }
            NavHostFragment.findNavController(this).navigate(R.id.action_from_event_to_manager)
//
        }
    }

}