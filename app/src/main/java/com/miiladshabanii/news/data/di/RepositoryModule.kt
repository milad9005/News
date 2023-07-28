package com.miiladshabanii.news.data.di

import com.miiladshabanii.news.data.repository.NewsRepositoryImpl
import com.miiladshabanii.news.domain.repository.NewsRepository
import com.miiladshabanii.news.model.News
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}