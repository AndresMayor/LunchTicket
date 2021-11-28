package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchticket.databinding.ActivityPostViewBinding
import java.text.SimpleDateFormat
import java.util.*

class PostViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostViewBinding
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.postBackBtn.setOnClickListener {
            finish()
        }
        post = intent.extras?.getSerializable("post") as Post
        binding.postViewTitle.text = post.title
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        binding.postViewDate.text = sdf.format(Date(post.date))
        binding.postViewMessage.text = post.message

    }
}