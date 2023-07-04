package com.miiladshabanii.news.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.miiladshabanii.news.model.News
import com.miiladshabanii.news.ui.base.BaseState
import com.miiladshabanii.news.ui.components.NewsItem
import com.miiladshabanii.news.ui.theme.NewsTheme

@Composable
fun Home(
    viewModel: HomeVM = hiltViewModel()
) {

    val viewState by viewModel.state.collectAsState()

    when (viewState.state) {
        BaseState.State.SUCCESS -> HomeContent(viewState.data)
        BaseState.State.LOADING -> {}
        BaseState.State.ERROR -> {}
    }
}

@Composable
private fun HomeContent(
    news: List<News>
) {
    LazyColumn {
        items(news) { item ->
            NewsItem(title = item.title, dateTime = item.dateTime, imageUrl = item.imageUrl)
        }
    }
}

@Composable
@Preview
private fun HomePreview() {
    NewsTheme {
        HomeContent(emptyList())
    }
}