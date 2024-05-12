package com.djvmil.entretienmentor.feature.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShimmerMovieItem(isEnableShimmer: Boolean) {
  Column(
      modifier = Modifier
      // .width(MovieItemWidth)
      // .clip(RoundedCornerShape(size = MovieItemRound))
      ) {
        /*ShimmerRectangle(
            isEnableShimmer = isEnableShimmer,
            width = MovieItemWidth,
            height = MovieItemHeight,
            round = MovieItemRound,
            padding = SmallPadding
        )

        ShimmerRectangle(
            isEnableShimmer = isEnableShimmer,
            width = MovieItemWidth,
            height = ShimmerRectangleHeight,
            round = MovieItemRound,
            padding = SmallPadding
        )*/
      }
}

@Preview @Composable private fun ShimmerMovieItemPreview() = ShimmerMovieItem(true)
