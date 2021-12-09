package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.adapters.RejectedAdapter
import com.example.lunchticket.databinding.ActivityFinancialRejectedLunchBinding
import com.example.lunchticket.model.LunchOrder
import java.util.*

class FinancialRejectedLunchActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RejectedAdapter
    private lateinit var binding: ActivityFinancialRejectedLunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialRejectedLunchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter = RejectedAdapter()
        layoutManager = LinearLayoutManager(this)
        binding.financialRejectedRecyclerView.layoutManager = layoutManager
        binding.financialRejectedRecyclerView.setHasFixedSize(true)
        binding.financialRejectedRecyclerView.adapter = adapter
        binding.closeRejectedBtn.setOnClickListener {
            finish()
        }
        adapter.addRejectedItem(
            LunchOrder(
                UUID.randomUUID().toString(),
                "Estudiante Prueba Segundo",
                "A00654321",
                "isabella12345",
                "Isabella",
                "rejected",
                Calendar.getInstance().time.time,
                ""
            )
        )

        adapter.addRejectedItem(
            LunchOrder(
                UUID.randomUUID().toString(),
                "Estudiante Prueba Tercero",
                "A00352321",
                "isabella12345",
                "Bristo",
                "rejected",
                Calendar.getInstance().time.time,
                ""
            )
        )
    }
}