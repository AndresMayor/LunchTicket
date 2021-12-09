package com.example.lunchticket.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.model.LunchOrder
import com.example.lunchticket.adapters.HistoryAdapter
import com.example.lunchticket.databinding.FragmentRestaurantHistoryBinding
import java.util.*

class RestaurantHistoryFragment : Fragment() {

    private var _binding: FragmentRestaurantHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: HistoryAdapter
    private var fragmentContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantHistoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        adapter = HistoryAdapter()
        layoutManager = LinearLayoutManager(activity)
        binding.restaurantHistoryRecyclerView.layoutManager = layoutManager
        binding.restaurantHistoryRecyclerView.setHasFixedSize(true)
        binding.restaurantHistoryRecyclerView.adapter = adapter

        // Spinner adapter
        val aaYear = ArrayAdapter<String>(fragmentContext!!, android.R.layout.simple_spinner_dropdown_item)
        aaYear.addAll("2021", "2022", "2023")
        binding.yearSpinner.adapter = aaYear

        adapter.addHistoryItem(
            LunchOrder(
                UUID.randomUUID().toString(),
                "Estudiante Prueba Primero",
                "A00123456",
                "isabella12345",
                "Isabella",
                Calendar.getInstance().time.time,
                ""
            )
        )
        return view
    }

    companion object {
        fun newInstance() = RestaurantHistoryFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            fragmentContext = context
        }
    }
}