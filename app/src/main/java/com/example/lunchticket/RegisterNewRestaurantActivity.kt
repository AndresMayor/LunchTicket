package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchticket.databinding.ActivityRegisterNewRestaurantBinding

class RegisterNewRestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterNewRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterNewRestaurantBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resCreatebBackBtn.setOnClickListener {
            finish()
        }
    }
}