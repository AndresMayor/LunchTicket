package com.example.lunchticket.model

import java.io.Serializable

data class LunchOrder(
    var id: String = "",
    var studentName: String = "",
    var studentIdentification: String = "",
    var restaurantId: String = "",
    var restaurantName: String = "",
    var date: Long = 0,
    var imageUrl: String = ""
): Serializable