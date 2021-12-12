package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.lunchticket.databinding.ActivityShowMapBinding

class ShowMapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.isabellaBtn.setOnClickListener(){
            binding.imgMaps.setImageResource(R.drawable.isabellaimg)
        }
        binding.snackBtn.setOnClickListener(){
            binding.imgMaps.setImageResource(R.drawable.snackimg)
        }
        binding.bristoBtn.setOnClickListener(){
            binding.imgMaps.setImageResource(R.drawable.bristoimg)
        }

        /**
        val spinner = binding.spinner

        val options = resources.getStringArray(R.array.opcionesMapa)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, options)
        binding.spinner.adapter = adapter



        val clicksListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@ShowMapActivity, "m", Toast.LENGTH_SHORT).show()

                when (position) {
                    1 -> binding.imgMaps.setImageResource(R.drawable.isabellaimg)
                    2 -> binding.imgMaps.setImageResource(R.drawable.bristoimg)
                    3 -> binding.imgMaps.setImageResource(R.drawable.snackimg)
                }

            }
        }
**/


    }
}