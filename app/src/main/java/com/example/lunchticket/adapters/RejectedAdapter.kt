package com.example.lunchticket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchticket.R
import com.example.lunchticket.model.LunchOrder
import com.example.lunchticket.views.HistoryView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RejectedAdapter: RecyclerView.Adapter<HistoryView>() {

    private var rejectedList = ArrayList<LunchOrder>()

    fun addRejectedItem(lunchOrder: LunchOrder) {
        rejectedList.add(lunchOrder)
        notifyItemInserted(rejectedList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryView {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.historyrow, parent,false)
        val historyView = HistoryView(row)
        return historyView
    }

    override fun onBindViewHolder(holder: HistoryView, position: Int) {
        val rejectedItem = rejectedList[position]
        holder.lunchOrder = rejectedItem
        holder.historyViewName.text = rejectedItem.studentName

        // Fecha
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val dateTime = sdf.format(Date(rejectedItem.date))
        val date = dateTime.toString().split(" ")[0]
        val hour = dateTime.toString().split(" ")[1]
        holder.historyViewDate.text = date
        holder.historyViewHour.text = hour
        holder.historyViewRestaurantName.text = rejectedItem.restaurantName
    }

    override fun getItemCount(): Int {
        return rejectedList.size
    }
}