package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.adapters.PostAdapter
import com.example.lunchticket.databinding.ActivityPostListBinding
import java.util.*

class PostListActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityPostListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter = PostAdapter()
        layoutManager = LinearLayoutManager(this)
        binding.postRecyclerView.layoutManager = layoutManager
        binding.postRecyclerView.setHasFixedSize(true)
        binding.postRecyclerView.adapter = adapter

        adapter.addPost(
            Post(
                UUID.randomUUID().toString(),
                "Prueba titulo",
                "Hola",
                Calendar.getInstance().time.time
            )
        )
    }
}