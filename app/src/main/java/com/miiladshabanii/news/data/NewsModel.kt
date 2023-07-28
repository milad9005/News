package com.miiladshabanii.news.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NewsModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author:String?,
    val title:String?,
    val description:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val content:String?,
)
