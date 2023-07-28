package com.miiladshabanii.news.data.remote.util

data class ErrorResponse(
    @SingleToArray val errors: List<String>,
)