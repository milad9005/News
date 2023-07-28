package com.miiladshabanii.news.ui.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.miiladshabanii.news.model.News
import com.miiladshabanii.news.ui.base.BaseState
import com.miiladshabanii.news.ui.components.NewsItem
import com.miiladshabanii.news.ui.components.NewsLoader
import com.miiladshabanii.news.ui.theme.NewsTheme
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    viewModel: HomeVM = hiltViewModel()
) {

    val viewState by viewModel.state.collectAsState()
    val news = viewModel.getNews().collectAsLazyPagingItems()

    HomeContent(news)



}


@Composable
private fun HomeContent(news: LazyPagingItems<News>) {
    LazyColumn {
        items(news.itemCount) {
            news[it]?.let { item ->
                NewsItem(
                    title = item.title,
                    dateTime = item.dateTime,
                    imageUrl = item.imageUrl
                )
            }
        }
    }
}


@Composable
@Preview
private fun HomePreview() {
    NewsTheme {

    }
}