package com.example.lunchticket.fragments

import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.databinding.FragmentSelectImageBinding
import com.example.lunchticket.databinding.FragmentSelectedPictureBinding
import android.R.attr.bitmap
import android.util.Base64
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lunchticket.*
import com.example.lunchticket.util.Constants
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import java.io.ByteArrayOutputStream
import com.android.volley.AuthFailureError

import com.android.volley.VolleyError

import com.android.volley.RequestQueue





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

//            val byteArrayOutputStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
//            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
//
//            val queue = Volley.newRequestQueue(context)
//            val url = "${Constants.BASE_URL}/userProfilePic?user=${Constants.user}"
//
//            val stringRequest = object: StringRequest(
//                Request.Method.POST, url,
//                { response ->
//                    var tok = response
//
//                },
//                { error -> Toast.makeText(context,"Verifica los datos", Toast.LENGTH_SHORT).show() }){
//                override fun getBody(): ByteArray {
//                    return encoded.toByteArray()
//                }
//            }
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