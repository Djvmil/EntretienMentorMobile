package com.djvmil.entretienmentor.feature.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djvmil.entretienmentor.core.ui.designsystem.theme.SmallPadding
import com.djvmil.entretienmentor.core.ui.widget.shimmer.ShimmerRectangle
import com.djvmil.entretienmentor.feature.util.ShimmerRectangleHeight

@Composable
fun ShimmerMovieItem(isEnableShimmer: Boolean) {
  Column(modifier = Modifier.width(100.dp).clip(RoundedCornerShape(size = 10.dp))) {
    ShimmerRectangle(
        isEnableShimmer = isEnableShimmer,
        width = 100.dp,
        height = 100.dp,
        round = 10.dp,
        padding = SmallPadding)

    ShimmerRectangle(
        isEnableShimmer = isEnableShimmer,
        width = 100.dp,
        height = ShimmerRectangleHeight,
        round = 10.dp,
        padding = SmallPadding)
  }
}

@Preview @Composable private fun ShimmerMovieItemPreview() = ShimmerMovieItem(true)
