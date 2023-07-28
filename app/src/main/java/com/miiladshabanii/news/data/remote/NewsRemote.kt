package com.miiladshabanii.news.data.remote

import com.miiladshabanii.news.data.NewsModel
import com.miiladshabanii.news.data.remote.service.NewsService
import com.miiladshabanii.news.data.remote.util.responseMapper
import javax.inject.Inject

class NewsRemote @Inject constructor(
    private val newsService: NewsService
) {
    suspend fun getNews(filter: String) = newsService.getNews(filter)


}