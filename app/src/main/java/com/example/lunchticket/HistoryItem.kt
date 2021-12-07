package com.example.lunchticket

import java.io.Serializable

data class HistoryItem(
    var id: String = "",
    var studentName: String = "",
    var studentIdentification: String= "",
    var date: Long = 0,
    var imageUrl: String = ""
): Serializable