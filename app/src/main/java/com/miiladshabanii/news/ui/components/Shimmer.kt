package com.miiladshabanii.news.ui.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miiladshabanii.news.ui.theme.NewsTheme

@Composable
fun Modifier.shimmerBackground(shape: androidx.compose.ui.graphics.Shape = MaterialTheme.shapes.large): Modifier =

        composed {
            val transition = rememberInfiniteTransition(label = "Shimmer")
            val translateAnimation by transition.animateFloat(
                initialValue = 0f,
                targetValue = 400f,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 2000, easing = LinearOutSlowInEasing),
                    RepeatMode.Restart
                ),
                label = "Shimmer",
            )
            val shimmerColors = listOf(
                Color.LightGray.copy(alpha = 0.9f),
                Color.LightGray.copy(alpha = 0.4f),
            )
            val brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnimation, translateAnimation),
                end = Offset(translateAnimation + 100f, translateAnimation + 100f),
                tileMode = TileMode.Mirror,
            )
            return@composed this.then(background(brush, shape))
        }

@Preview
@Composable
fun ShimmerPreview() {
    NewsTheme {
        Box(
            modifier = Modifier
                .size(128.dp)
                .shimmerBackground()
        )
    }
}