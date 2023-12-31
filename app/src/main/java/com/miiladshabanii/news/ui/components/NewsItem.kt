package com.miiladshabanii.news.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.miiladshabanii.news.R
import com.miiladshabanii.news.ui.theme.NewsTheme
import kotlinx.coroutines.Dispatchers

@Composable
fun NewsItem(
    title: String,
    dateTime: String,
    imageUrl: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .dispatcher(Dispatchers.IO)
                .diskCacheKey(imageUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .error(R.drawable.ic_image_not_supported)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.large),
            error = {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_image_not_supported),
                    contentDescription = null
                )
            }
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            Text(
                text = title, style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = dateTime,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}


@Composable
private fun NewsItemLoader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Box(
            modifier = Modifier
                .size(64.dp)
                .shimmerBackground()
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .shimmerBackground()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .shimmerBackground()
            )

        }
    }
}

@Composable
fun NewsLoader() {
    val config = LocalConfiguration.current
    LazyColumn {
        items(count = config.screenHeightDp.div(72).plus(1)) {
            NewsItemLoader()
        }
    }
}

@Composable
@Preview
private fun NewsItemLoaderPreview() {
    NewsTheme {
       NewsLoader()
    }
}

@Composable
@Preview
private fun NewsItemPreview() {
    NewsTheme {
        NewsItem(
            title = "This is a News Title",
            dateTime = "2 hour ago",
            imageUrl = "https://yt3.googleusercontent.com/y_esGAQOhX4rTpWvrALErAJlFbm_2TIVrvcVfcZny7TuA8dJZgOQcC6KRfd_J5hljFe-foYXj9U=s900-c-k-c0x00ffffff-no-rj"
        )
    }
}