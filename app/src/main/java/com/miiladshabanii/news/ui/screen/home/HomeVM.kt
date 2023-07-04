package com.miiladshabanii.news.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miiladshabanii.news.domain.base.Result
import com.miiladshabanii.news.domain.usecase.NewsUseCase
import com.miiladshabanii.news.model.News
import com.miiladshabanii.news.ui.base.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.defaultInitState)
    val state: StateFlow<HomeState> = _state

    init {
        getNews()
    }

    private fun getNews() =
        viewModelScope.launch(Dispatchers.IO) {
            newsUseCase.invoke(false).collectLatest {
                when (it) {
                    is Result.Error -> TODO()
                    Result.Loading -> TODO()
                    is Result.Success -> _state.emit(
                        HomeState(
                            state = BaseState.State.SUCCESS,
                            data = it.data,
                            errorMsg = null
                        )
                    )
                }
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