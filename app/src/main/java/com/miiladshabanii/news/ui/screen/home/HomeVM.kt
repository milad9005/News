package com.miiladshabanii.news.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.domain.usecase.NewsUseCase
import com.miiladshabanii.news.model.News
import com.miiladshabanii.news.ui.base.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.defaultInitState)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getNews()
    }

    fun getNews() = newsUseCase.invoke(false).map {
        it.map { news ->
            News(
                title = news.title?:"",
                content = news.content?:"",
                author = news.author ?:"",
                dateTime = news.publishedAt?:"",
                desc = news.description?:"",
                imageUrl = news.urlToImage?:""
            )
        }
    }
}

data class HomeState(
    override val state: BaseState.State,
    override val errorMsg: String?,
    override val data: List<News> = emptyList()
) : BaseState {
    companion object {
        val defaultInitState = HomeState(
            state = BaseState.State.LOADING,
            errorMsg = null
        )
    }
}