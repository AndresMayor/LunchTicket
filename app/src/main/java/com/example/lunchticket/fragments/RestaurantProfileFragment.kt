package com.example.lunchticket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentRestaurantProfileBinding
import com.example.lunchticket.util.Constants

class RestaurantProfileFragment : Fragment() {

    private var _binding: FragmentRestaurantProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.restaurantNitProfileTV.text = Constants.code
        binding.restaurantProfileNameTV.text = Constants.name
        binding.restaurantProfileUserTV.text = Constants.user
        return view
    }

    companion object {
        fun newInstance() = RestaurantProfileFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}