package com.example.lunchticket.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.PostListActivity
import com.example.lunchticket.ProfilePictureActivity
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // Fragmento para pantalla principal de estudiante

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.homePostBtn.setOnClickListener {
            val intent = Intent(context, PostListActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}