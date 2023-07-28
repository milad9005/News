package com.miiladshabanii.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class News(
    val title: String,
    val dateTime: String,
    val imageUrl: String,
    val desc: String,
    val content: String,
    val author: String
)
