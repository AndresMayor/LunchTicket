package com.example.lunchticket

import java.io.Serializable

// Clase para las publicaciones de apoyo financiero

data class Post(
    var id: String = "",
    var title: String = "",
    var message: String = "",
    var date: Long = 0
) : Serializable