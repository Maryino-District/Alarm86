package com.example.alarm86.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.alarm86.R
import com.example.alarm86.databinding.FragmentAlarmManagerBinding

class AlarmManagerFragment : Fragment() {
    private val ALARM_MANAGER_FRAGMENT_TAG = "ALARM_MANAGER_FRAGMENT_TAG"
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnManagerAdd.setOnClickListener {
            Log.d(ALARM_MANAGER_FRAGMENT_TAG, "AMP: on view Created")
            NavHostFragment.findNavController(this).navigate(R.id.action_from_manager_to_event)
        }
    }

}