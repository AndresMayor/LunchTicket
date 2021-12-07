package com.example.lunchticket.views

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchticket.HistoryItem
import com.example.lunchticket.R

class HistoryView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var historyItem: HistoryItem? = null
    var historyViewName: TextView = itemView.findViewById(R.id.historyViewNameTV)
    var historyViewDate: TextView = itemView.findViewById(R.id.historyViewDateTV)
    var historyViewHour: TextView = itemView.findViewById(R.id.historyViewHourTV)
    var historyLayout: ConstraintLayout = itemView.findViewById(R.id.historyViewLayout)


}