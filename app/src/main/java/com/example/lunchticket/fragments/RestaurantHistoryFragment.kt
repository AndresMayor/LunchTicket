package com.example.lunchticket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.HistoryItem
import com.example.lunchticket.R
import com.example.lunchticket.adapters.HistoryAdapter
import com.example.lunchticket.databinding.FragmentRestaurantHistoryBinding
import java.util.*

class RestaurantHistoryFragment : Fragment() {

    private var _binding: FragmentRestaurantHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantHistoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        adapter = HistoryAdapter()
        layoutManager = LinearLayoutManager(activity)
        binding.restaurantRecyclerView.layoutManager = layoutManager
        binding.restaurantRecyclerView.setHasFixedSize(true)
        binding.restaurantRecyclerView.adapter = adapter

        adapter.addHistoryItem(HistoryItem(
            UUID.randomUUID().toString(),
            "Estudiante Prueba Primero",
            "A00123456",
            Calendar.getInstance().time.time,
            ""
        ))
        return view
    }

    companion object {
        fun newInstance() = RestaurantHistoryFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}