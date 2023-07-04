package com.miiladshabanii.news.domain.repository

import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun news(): Flow<Result<List<News>>>
}