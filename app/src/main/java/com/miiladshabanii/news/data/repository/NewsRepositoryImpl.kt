package com.miiladshabanii.news.data.repository

import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.data.FakeData
import com.miiladshabanii.news.domain.repository.NewsRepository
import com.miiladshabanii.news.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(

) : NewsRepository {
    override fun news(): Flow<Result<List<News>>> = flow {
        emit(Result.Success(FakeData.listOfNews))
    }


}