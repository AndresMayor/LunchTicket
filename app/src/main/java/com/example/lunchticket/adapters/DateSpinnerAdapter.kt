package com.example.lunchticket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.lunchticket.R
import com.example.lunchticket.model.DateItem

class DateSpinnerAdapter(context: Context) : BaseAdapter() {

    private var dateItemList = ArrayList<DateItem>()
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    fun addDateItem(dateItem: DateItem) {
        dateItemList.add(dateItem)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return dateItemList.size
    }

    override fun getItem(position: Int): Any {
        return dateItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.inflater.inflate(R.layout.spinner_view, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text = dateItemList[position].name
        return view!!
    }

}

private class ListRowHolder(row: View?) {
    val label: TextView = row?.findViewById(R.id.label) as TextView
}