package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchticket.databinding.ActivityFinancialRestaurantsDataBinding

class FinancialRestaurantsDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinancialRestaurantsDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialRestaurantsDataBinding.inflate(layoutInflater)
        val view = binding.root
        binding.closeRestaurantDataBtn.setOnClickListener {
            finish()
        }
        setContentView(view)
    }
}