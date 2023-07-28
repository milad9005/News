package com.miiladshabanii.news.data.remote.service

import com.miiladshabanii.news.data.NewsModel
import com.miiladshabanii.news.data.remote.ApiResponse
import com.miiladshabanii.news.data.remote.util.ErrorResponse
import com.miiladshabanii.news.data.remote.util.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/everything")
   suspend fun getNews(@Query("q") filter: String): NetworkResponse<ApiResponse<List<NewsModel>>, ErrorResponse>

}