package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchticket.databinding.ActivityRegisterNewStudentBinding

class RegisterNewStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterNewStudentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.stuCreateBackBtn.setOnClickListener {
            finish()
        }
    }
}