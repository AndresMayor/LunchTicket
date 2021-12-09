package com.example.lunchticket

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.lunchticket.databinding.ActivityRegisterNewRestaurantBinding
import com.example.lunchticket.dialog.SelectImageFragment

class RegisterNewRestaurantActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterNewRestaurantBinding
    lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterNewRestaurantBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.restaurantImageBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val dialog = SelectImageFragment()
                dialog.show(supportFragmentManager, "SelectImage")
            } else {
                requestPermissions(arrayOf(
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
            }
        }

        binding.resCreateBackBtn.setOnClickListener {
            finish()
        }
    }
}