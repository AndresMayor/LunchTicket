package com.example.lunchticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.adapters.PostAdapter
import com.example.lunchticket.databinding.ActivityPostListBinding
import java.util.*

class PostListActivity : AppCompatActivity() {

    // Actividad que muestra las publicaciones de apoyo financiero, puede servir tanto para
    // estudiantes como para apoyo financiero

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

        binding.closePostListBtn.setOnClickListener {
            finish()
        }

        binding.createNewPostBtn.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        adapter.addPost(
            Post(
                UUID.randomUUID().toString(),
                "Prueba titulo",
                "Con la nuevo aplicación LunchTicket, los beneficiarios podrán  solicitar sus almuerzos y los restaurantes podrán llevar seguimiento de los almuerzos que han dado.",
                Calendar.getInstance().time.time
            )
        )
    }
}