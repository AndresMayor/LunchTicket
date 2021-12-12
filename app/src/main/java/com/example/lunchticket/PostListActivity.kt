package com.example.lunchticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lunchticket.adapters.PostAdapter
import com.example.lunchticket.databinding.ActivityPostListBinding
import com.example.lunchticket.model.Post
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList

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
        getPostList()
       
    }

    private fun getPostList(){
        Firebase.firestore.collection("posts")
            .orderBy("date").addSnapshotListener { value, error ->
                for (change in value!!.documentChanges) {
                    //Log.e(">>>", value.documents.size.toString())
                    when (change.type) {
                        DocumentChange.Type.ADDED -> {
                            val post = change.document.toObject(Post::class.java)
                            adapter.addPost(post)
                        }

                        DocumentChange.Type.REMOVED -> {
                            val deletedPokemon = change.document.toObject(Post::class.java)
                            //Log.e(">>>", deletedPokemon.name)
                            adapter.removePokemon(deletedPokemon)
                        }
                    }
                }
            }

        /*Firebase.firestore.collection("posts").orderBy("date", Query.Direction.DESCENDING).get()
            .addOnCompleteListener { task ->
                for (doc in task.result!!) {
                    val post = doc.toObject(Post::class.java)
                    Log.e(">>>", post.title)
                    adapter.addPost(post)
                }
            }*/
    }

}