package com.example.lunchticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import com.example.lunchticket.databinding.ActivityCreatePostBinding
import com.example.lunchticket.model.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.sendPostBtn.setOnClickListener {
            val postId = UUID.randomUUID().toString();
            val postTitle = binding.postTitleET.text.toString()
            val postDescription = binding.postDescriptionET.text.toString()
            val postDate = Calendar.getInstance().time.time
            val post = Post(postId, postTitle, postDescription, postDate)
            Firebase.firestore.collection("posts").document(postId).set(post)
            Toast.makeText(this, "Publicación creada con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.createBackBtn.setOnClickListener {
            finish()
        }
    }

}