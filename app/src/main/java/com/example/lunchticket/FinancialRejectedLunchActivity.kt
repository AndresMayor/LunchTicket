package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchticket.databinding.ActivityFinancialRejectedLunchBinding

class FinancialRejectedLunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinancialRejectedLunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialRejectedLunchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}