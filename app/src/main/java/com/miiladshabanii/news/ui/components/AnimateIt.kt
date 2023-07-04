package com.miiladshabanii.news.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import com.miiladshabanii.news.R
import com.miiladshabanii.news.ui.theme.NewsTheme


@Composable
fun AnimateIt(
    modifier: Modifier = Modifier,
    loader: Int,
    _speed: Float = 2f,
    background: Color = Color.White,
) {
    val anim = rememberLottieAnimatable()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(loader))
    val speed by remember { mutableStateOf(_speed) }

    LaunchedEffect(composition, speed) {
        anim.animate(
            composition, iteration = LottieConstants.IterateForever, speed = speed,
        )
    }
    LottieAnimation(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        composition = anim.composition,
        progress = { anim.progress })

}


@Composable
@Preview
private fun ImageLoaderPreview() {
    NewsTheme {
        AnimateIt(loader = R.raw.image_loading)
    }
}