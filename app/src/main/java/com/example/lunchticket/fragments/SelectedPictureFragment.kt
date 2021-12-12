package com.example.lunchticket.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.LoginActivity
import com.example.lunchticket.MainActivity
import com.example.lunchticket.ProfilePictureActivity
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentSelectImageBinding
import com.example.lunchticket.databinding.FragmentSelectedPictureBinding

class SelectedPictureFragment : Fragment() {

    // Fragmento para mostrar la foto que tomo el estudiante

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
        val thumbnail = Bitmap.createScaledBitmap(bitmap!!, bitmap.width /4, bitmap.height / 4,true)

        lateinit var rotatedThumbnail: Bitmap

        if(bitmap.width > bitmap.height) {
            val matrix = Matrix()
            matrix.postRotate(90f)
            rotatedThumbnail = Bitmap.createBitmap(
                thumbnail, 0, 0, thumbnail.width, thumbnail.height, matrix, true)
            binding.previewImage.setImageBitmap(rotatedThumbnail)
        } else {
            binding.previewImage.setImageBitmap(thumbnail)
        }

        binding.submitPictureBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.cancelPictureBtn.setOnClickListener {
            (activity as ProfilePictureActivity).supportFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        fun newInstance() = SelectedPictureFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}