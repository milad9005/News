package com.miiladshabanii.news.di

import com.miiladshabanii.news.domain.repository.NewsRepository
import com.miiladshabanii.news.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideNewsUseCase(
        newsRepository: NewsRepository
    ): NewsUseCase = NewsUseCase(newsRepository)


}