package com.example.alarm86.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.alarm86.R
import com.example.alarm86.databinding.FragmentAlarmManagerBinding
import com.example.alarm86.ui.AlarmModel
import com.example.alarm86.ui.AlarmViewModel

class AlarmManagerFragment : Fragment() {
    private val ALARM_MANAGER_FRAGMENT_TAG = "ALARM_MANAGER_FRAGMENT_TAG"
    private lateinit var binding: FragmentAlarmManagerBinding
    private val viewmodel: AlarmViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(ALARM_MANAGER_FRAGMENT_TAG, "AMF: onCreateView")

        binding = FragmentAlarmManagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnManagerAdd.setOnClickListener {
            Log.d(ALARM_MANAGER_FRAGMENT_TAG, "AMP: on view Created, selected time is ${binding.timePicker.minute}:${binding.timePicker.minute}")
            binding.tvTimeAlarmManager.apply {
                visibility = View.VISIBLE
                text = getString(R.string.alarm_manager_time, binding.timePicker.hour, binding.timePicker.minute)
            }
            binding.switchAlarmManager.visibility = View.VISIBLE
            viewmodel.addAlarm(
                AlarmModel(
                    hour = binding.timePicker.hour,
                    minute = binding.timePicker.minute,
                    isEnabledAlarm = false,
                ))
        }


        binding.switchAlarmManager.setOnCheckedChangeListener { compoundButton, isEnabled ->
            if (isEnabled) {
                viewmodel.addAlarm(AlarmModel(viewmodel.data.value?.first()?.hour, viewmodel.data.value?.first()?.minute, isEnabledAlarm = isEnabled ))
                viewmodel.scheduleAlarm(binding.timePicker.hour, binding.timePicker.minute)
            } else viewmodel.cancelPreviousAlarm()
        }

        viewmodel.data.observe(viewLifecycleOwner) {
            viewmodel.data.value?.getOrNull(0)?.let {
                binding.tvTimeAlarmManager.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.alarm_manager_time, it.hour, it.minute)
                }
                binding.switchAlarmManager.apply {
                    visibility = View.VISIBLE
                    isChecked = it.isEnabledAlarm
                }
            }
        }
    }
}