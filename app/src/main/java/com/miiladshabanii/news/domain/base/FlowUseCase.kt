package com.miiladshabanii.news.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<out R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(forceRefresh: Boolean): Flow<R> = execute(forceRefresh)
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(refresh: Boolean): Flow<R>
}