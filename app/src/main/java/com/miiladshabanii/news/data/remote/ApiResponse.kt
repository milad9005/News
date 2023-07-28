package com.miiladshabanii.news.data.remote

data class ApiResponse<T>(
    val status: String,
    val totalResults: Long,
    val articles: T
)
