package com.example.lunchticket.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.adapters.DateSpinnerAdapter
import com.example.lunchticket.adapters.HistoryAdapter
import com.example.lunchticket.databinding.FragmentRestaurantHistoryBinding
import com.example.lunchticket.model.DateItem
import com.example.lunchticket.model.LunchOrder
import java.text.DateFormatSymbols
import java.time.YearMonth
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
        val yearSpinnerAdapter = DateSpinnerAdapter(fragmentContext!!)
        yearSpinnerAdapter.addDateItem(DateItem(2021, "2021"))
        yearSpinnerAdapter.addDateItem(DateItem(2022, "2022"))
        yearSpinnerAdapter.addDateItem(DateItem(2023, "2023"))
        binding.yearSpinner.adapter = yearSpinnerAdapter

        val monthSpinnerAdapter = DateSpinnerAdapter(fragmentContext!!)
        val months: Array<String> = DateFormatSymbols().months
        months.forEachIndexed { index, element ->
            val monthItem = DateItem(index, element.replaceFirstChar {it.uppercase()})
            monthSpinnerAdapter.addDateItem(monthItem)
        }
        binding.monthSpinner.adapter = monthSpinnerAdapter

        val daySpinnerAdapter = DateSpinnerAdapter(fragmentContext!!)
        var dayIndex = 1
        while (dayIndex < 32) {
            daySpinnerAdapter.addDateItem(DateItem(dayIndex, dayIndex.toString()))
            dayIndex++
        }
        binding.daySpinner.adapter = daySpinnerAdapter

        adapter.addHistoryItem(
            LunchOrder(
                UUID.randomUUID().toString(),
                "Estudiante Prueba Primero",
                "A00123456",
                "isabella12345",
                "Isabella",
                "approved",
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