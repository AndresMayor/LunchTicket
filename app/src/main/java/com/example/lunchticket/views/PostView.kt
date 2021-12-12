package com.example.lunchticket.views

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchticket.model.Post
import com.example.lunchticket.PostViewActivity
import com.example.lunchticket.R

class PostView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var post: Post? = null
    var postViewTitle: TextView = itemView.findViewById(R.id.previewPostTitleTV)
    var postViewDate: TextView = itemView.findViewById(R.id.previewPostDateTV)
    var postLayout: ConstraintLayout = itemView.findViewById(R.id.postLayout)

    init {
        postLayout.setOnClickListener {
            val intent = Intent(postLayout.context, PostViewActivity::class.java).apply {
                putExtra("post", post)
            }
            postLayout.context.startActivity(intent)
        }
    }

}