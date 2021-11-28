package com.example.lunchticket

import java.io.Serializable

data class Post(
    var id: String = "",
    var title: String = "",
    var message: String = "",
    var date: Long = 0
) : Serializable