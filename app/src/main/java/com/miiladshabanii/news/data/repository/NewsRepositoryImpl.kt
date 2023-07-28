package com.miiladshabanii.news.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.miiladshabanii.news.data.NewsModel
import com.miiladshabanii.news.data.db.dao.NewsDao
import com.miiladshabanii.news.data.remote.NewsRemote
import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.data.remote.util.FakeData
import com.miiladshabanii.news.data.remote.util.NetworkResponse
import com.miiladshabanii.news.domain.repository.NewsRepository
import com.miiladshabanii.news.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemote: NewsRemote,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun news(): Flow<PagingData<NewsModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 50,
                prefetchDistance = 50,
                initialLoadSize = 50,
            ),
            pagingSourceFactory = {
                newsDao.getNews()
            }
        ).flow
            .catch { e ->
                e.printStackTrace()
            }
            .also {
                CoroutineScope(Dispatchers.IO).launch {
                    when(val data = withContext(Dispatchers.IO) { newsRemote.getNews("us") }){
                        is NetworkResponse.NetworkError -> throw data.error
                        is NetworkResponse.ServerError -> throw Exception(data.body?.errors?.first())
                        is NetworkResponse.Success -> {
                            newsDao.delete()
                            newsDao.insert(data.body.articles)
                        }
                        is NetworkResponse.UnknownError -> throw data.error
                    }
                }
            }
}