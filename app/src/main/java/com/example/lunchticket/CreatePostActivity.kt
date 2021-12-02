package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchticket.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.createBackBtn.setOnClickListener {
            finish()
        }
    }
}