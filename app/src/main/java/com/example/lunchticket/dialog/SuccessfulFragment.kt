package com.example.lunchticket.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentSuccessfulBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuccessfulFragment : DialogFragment() {

    private lateinit var binding: FragmentSuccessfulBinding

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuccessfulBinding.inflate(inflater, container, false)
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            activity?.finish()
        }
        return binding.root
    }

    companion object {
        fun newInstance() = SuccessfulFragment()
    }
}