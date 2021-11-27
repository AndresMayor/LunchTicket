package com.example.lunchticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000)
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}