package com.example.lunchticket

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.adapters.PostAdapter
import com.example.lunchticket.databinding.ActivityPostListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class PostListActivity : AppCompatActivity() {

    // Actividad que muestra las publicaciones de apoyo financiero, puede servir tanto para
    // estudiantes como para apoyo financiero

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityPostListBinding
    private var posts: ArrayList<Post> = ArrayList()


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
        getPostList()
       
    }

    fun getPostList(){
        Firebase.firestore.collection("posts").get()
            .addOnCompleteListener { task ->
                for (doc in task.result!!) {
                    val post = doc.toObject(Post::class.java)
                    Log.e(">>>", post.title)
                    adapter.addPost(post)
                    posts.add(post)
                }
            }
    }


}