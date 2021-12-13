package com.example.lunchticket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentRestaurantHomeBinding
import com.example.lunchticket.util.Constants

class RestaurantHomeFragment : Fragment() {

    // Fragmento de la pantalla principal de restaurante

    private var _binding: FragmentRestaurantHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.homeRestaurantNameTV.text = Constants.name
        return view
    }

    companion object {
        fun newInstance() = RestaurantHomeFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}