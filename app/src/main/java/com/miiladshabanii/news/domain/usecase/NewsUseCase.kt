package com.miiladshabanii.news.domain.usecase

import com.miiladshabanii.news.domain.base.FlowUseCase
import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.domain.repository.NewsRepository
import com.miiladshabanii.news.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
):FlowUseCase<List<News>>(coroutineDispatcher = Dispatchers.IO) {
    override fun execute(refresh: Boolean): Flow<Result<List<News>>>  = newsRepository.news()
}