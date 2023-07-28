package com.miiladshabanii.news.domain.usecase

import androidx.paging.PagingData
import com.miiladshabanii.news.data.NewsModel
import com.miiladshabanii.news.domain.base.FlowUseCase
import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.domain.repository.NewsRepository
import com.miiladshabanii.news.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class NewsUseCase(
    private val newsRepository: NewsRepository
) : FlowUseCase<PagingData<NewsModel>>(coroutineDispatcher = Dispatchers.IO) {
    override fun execute(refresh: Boolean): Flow<PagingData<NewsModel>> = newsRepository.news()
}