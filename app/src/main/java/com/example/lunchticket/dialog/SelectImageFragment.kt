package com.example.lunchticket.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentSelectImageBinding

class SelectImageFragment : DialogFragment() {

    // Es un dialogFragment para elegir entre galeria y camara, se usa en el registro de
    // nuevos restaurantes

    private lateinit var binding: FragmentSelectImageBinding

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = SelectImageFragment()
    }
}