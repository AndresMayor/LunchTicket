package com.example.lunchticket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchticket.model.Post
import com.example.lunchticket.R
import com.example.lunchticket.views.PostView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostAdapter : RecyclerView.Adapter<PostView>() {

    // Adaptador para lista de publicaciones
    private val postList = ArrayList<Post>()

    fun addPost(post: Post) {
        postList.add(0, post)
        notifyItemInserted(0)
    }

    fun removePokemon(post: Post) {
        val index = postList.indexOf(post)
        postList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clearList() {
        postList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostView {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.postrow, parent, false)
        val postView = PostView(row)
        return postView
    }

    override fun onBindViewHolder(holder: PostView, position: Int) {
        val post = postList[position]
        holder.post = post
        holder.postViewTitle.text = post.title
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        holder.postViewDate.text = sdf.format(Date(post.date))
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}