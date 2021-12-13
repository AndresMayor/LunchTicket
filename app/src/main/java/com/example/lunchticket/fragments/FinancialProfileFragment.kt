package com.example.lunchticket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentFinancialProfileBinding
import com.example.lunchticket.util.Constants

class FinancialProfileFragment : Fragment() {

    private var _binding: FragmentFinancialProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFinancialProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.financialNameTV.text = Constants.name
        binding.financialProfileIdTV.text = Constants.doc
        return view
    }

    companion object {
        fun newInstance() = FinancialProfileFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}