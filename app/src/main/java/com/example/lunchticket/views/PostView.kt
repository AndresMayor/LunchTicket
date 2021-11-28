package com.example.lunchticket.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchticket.Post
import com.example.lunchticket.R

class PostView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var post: Post? = null
    var postViewTitle: TextView = itemView.findViewById(R.id.previewPostTitleTV)
    var postViewDate: TextView = itemView.findViewById(R.id.previewPostDateTV)

}