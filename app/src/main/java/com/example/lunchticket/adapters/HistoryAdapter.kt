package com.example.lunchticket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchticket.HistoryItem
import com.example.lunchticket.R
import com.example.lunchticket.views.HistoryView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryAdapter: RecyclerView.Adapter<HistoryView>() {

    private var historyList = ArrayList<HistoryItem>()

    fun addHistoryItem(historyItem: HistoryItem) {
        historyList.add(historyItem)
        notifyItemInserted(historyList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryView {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.historyrow, parent,false)
        val historyView = HistoryView(row)
        return historyView
    }

    override fun onBindViewHolder(holder: HistoryView, position: Int) {
        val historyItem = historyList[position]
        holder.historyItem = historyItem
        holder.historyViewName.text = historyItem.studentName

        // Fecha
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        //holder.historyViewDate.text = sdf.format(Date(historyItem.date))

        val dateTime = sdf.format(Date(historyItem.date))
        val date = dateTime.toString().split(" ")[0]
        val hour = dateTime.toString().split(" ")[1]
        holder.historyViewDate.text = date
        holder.historyViewHour.text = hour

        // Hora
        /*val cal = Calendar.getInstance()
        cal.time = date
        val hours = cal.get(Calendar.HOUR_OF_DAY)
        holder.historyViewHour.text = hours*/
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}