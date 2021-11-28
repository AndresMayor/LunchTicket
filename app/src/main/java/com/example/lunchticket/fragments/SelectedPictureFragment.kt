package com.example.lunchticket.fragments

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.LoginActivity
import com.example.lunchticket.ProfilePictureActivity
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentSelectImageBinding
import com.example.lunchticket.databinding.FragmentSelectedPictureBinding

class SelectedPictureFragment : Fragment() {

    private var _binding: FragmentSelectedPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectedPictureBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val bitmap = (activity as ProfilePictureActivity).bitmap
        val thumbnail = Bitmap.createScaledBitmap(bitmap!!, bitmap.width / 4, bitmap.height / 4, true)
        binding.previewImage.setImageBitmap(thumbnail)

        binding.cancelPictureBtn.setOnClickListener {
            (activity as ProfilePictureActivity).supportFragmentManager.popBackStack()
        }
        return view
    }

    companion object {
        fun newInstance() = SelectedPictureFragment()
    }
}