package com.djvmil.entretienmentor.feature.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djvmil.entretienmentor.feature.R
import com.djvmil.entretienmentor.feature.ui.community.model.CommunityUiModel
import com.djvmil.entretienmentor.feature.util.ShimmerMovieItemCount
import kotlinx.collections.immutable.PersistentList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel(), onShowDetail: (movieId: Int) -> Unit) {
  HomeContent(viewModel = viewModel, modifier = Modifier, onShowDetail = onShowDetail)
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier,
    onShowDetail: (movieId: Int) -> Unit
) {
  val uiState by viewModel.uiMovies.collectAsState()
  var isEnableShimmer by remember { mutableStateOf(false) }

  LaunchedEffect(
      key1 = true,
  ) {
    viewModel.getCommunities()
  }

  Column(modifier = modifier.fillMaxWidth()) {
    TopBanner()

    /*  when (uiState) {
        ResultEM.Loading -> {
            isEnableShimmer = true

            ShowShimmerMovies(
                isEnableShimmer = true,
                count = ShimmerMovieItemCount
            )
        }

        is ResultEM.Failure -> {
            //Todo: Implement Error Handling
        }

        is ResultEM.Success -> {
            isEnableShimmer = false

            val movies = (uiState as ResultEM.Success<PersistentList<MovieUiModel>>).value

            if (movies.isEmpty()) {
                EmptyMovies()
            } else {
                ShowMovies(
                    movies = movies,
                    onShowDetail = onShowDetail
                )
            }
        }
    }*/
  }
}

@Composable
fun TopBanner() {
  Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.height(100.dp).padding(horizontal = 8.dp, vertical = 8.dp)
      // .clip(Shapes.)
      ) {
        Column(modifier = Modifier.weight(0.4f)) {
          Text(
              text = "Entretien",
              fontSize = 28.sp,
              fontFamily = FontFamily(Font(R.font.josefin_sans_semibold)),
              fontWeight = FontWeight.ExtraBold,
              lineHeight = 0.sp,
          )

          Text(
              modifier = Modifier.weight(0.4f),
              text = "Mentor",
              fontSize = 28.sp,
              textAlign = TextAlign.End,
              lineHeight = 0.sp,
              fontFamily = FontFamily(Font(R.font.josefin_sans_semibold)),
              fontWeight = FontWeight.Medium)
        }

        Icon(
            modifier = Modifier.weight(0.1f).size(25.dp),
            painter = painterResource(id = R.drawable.outline_bell),
            contentDescription = "notification")
      }
}

@Composable
fun ShowCommunities(
    communities: PersistentList<CommunityUiModel>,
    onShowDetail: (communityId: Int) -> Unit
) {
  val scrollState = rememberScrollState()

  LazyRow(Modifier.verticalScroll(scrollState)) {
    items(items = communities, key = { community -> community.id!! }) { community ->
      CommunityItem(community = community, onShowDetail = onShowDetail)
    }
  }
}

@Composable
fun ShowShimmerMovies(isEnableShimmer: Boolean, count: Int) {
  LazyRow() { items(count = count) { ShimmerMovieItem(isEnableShimmer = isEnableShimmer) } }
}

@Preview
@Composable
private fun TopBannerPreview() {
  TopBanner()
}

@Preview
@Composable
private fun EmptyMoviesPreview() {
  // EmptyMovies()
}

@Preview
@Composable
private fun ShowShimmerMoviesPreview() {
  ShowShimmerMovies(isEnableShimmer = true, count = ShimmerMovieItemCount)
}
