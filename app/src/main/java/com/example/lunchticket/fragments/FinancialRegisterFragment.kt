package com.example.lunchticket.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.PostListActivity
import com.example.lunchticket.R
import com.example.lunchticket.RegisterNewRestaurantActivity
import com.example.lunchticket.databinding.FragmentFinancialRegisterBinding

class FinancialRegisterFragment : Fragment() {

    private var _binding: FragmentFinancialRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFinancialRegisterBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.registerNewRestaurantBtn.setOnClickListener {
            val intent = Intent(context, RegisterNewRestaurantActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    companion object {
        fun newInstance() = FinancialRegisterFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}