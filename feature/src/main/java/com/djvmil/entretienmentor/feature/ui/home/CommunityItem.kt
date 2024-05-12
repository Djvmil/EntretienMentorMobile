package com.djvmil.entretienmentor.feature.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.djvmil.entretienmentor.core.ui.designsystem.theme.DetailIcon
import com.djvmil.entretienmentor.core.ui.designsystem.theme.HighPadding
import com.djvmil.entretienmentor.core.ui.designsystem.theme.MovieDetailItemTextStyle
import com.djvmil.entretienmentor.core.ui.designsystem.theme.NormalPadding
import com.djvmil.entretienmentor.core.ui.designsystem.theme.SmallEvelation
import com.djvmil.entretienmentor.core.ui.designsystem.theme.SmallPadding
import com.djvmil.entretienmentor.feature.ui.community.model.CommunityUiModel

@Composable
fun CommunityItem(community: CommunityUiModel, onShowDetail: (movieId: Int) -> Unit) {
  val context = LocalContext.current

  Surface(
      modifier = Modifier.background(Color.White).padding(all = SmallPadding).wrapContentSize(),
      // .clip(RoundedCornerShape(size = MovieItemRound)),
      shadowElevation = SmallEvelation,
      tonalElevation = SmallEvelation) {
        ConstraintLayout(
            modifier = Modifier.wrapContentSize().clickable { onShowDetail(community.id!!) }) {
              val (image, gradiant, textComment, iconComment, textLike, iconLike, isLiked) =
                  createRefs()

              Image(
                  modifier =
                      Modifier
                          // .size(width = MovieItemWidth, height = MovieItemHeight)
                          .constrainAs(image) {
                            linkTo(
                                start = parent.start,
                                end = parent.end,
                                top = parent.top,
                                bottom = gradiant.bottom)
                          },
                  painter =
                      rememberAsyncImagePainter(
                          model = ImageRequest.Builder(context).data(community.name).build()),
                  contentScale = ContentScale.Crop,
                  contentDescription = null)

              Icon(
                  modifier =
                      Modifier.padding(start = NormalPadding, top = SmallPadding)
                          .size(DetailIcon)
                          .constrainAs(iconComment) {
                            start.linkTo(image.start)
                            top.linkTo(image.bottom)
                          },
                  imageVector = Icons.Filled.MailOutline,
                  tint = Color.Blue,
                  contentDescription = "")
              Text(
                  text = community.name.toString(),
                  overflow = TextOverflow.Ellipsis,
                  maxLines = 1,
                  style = MovieDetailItemTextStyle,
                  color = Color.Blue,
                  modifier =
                      Modifier.padding(start = NormalPadding, top = SmallPadding).constrainAs(
                          textComment) {
                            start.linkTo(iconComment.end)
                            top.linkTo(iconComment.top)
                            bottom.linkTo(iconComment.bottom)
                          })
              Icon(
                  imageVector = Icons.Outlined.Favorite,
                  contentDescription = "",
                  tint = Color.Red,
                  modifier =
                      Modifier.padding(
                              start = HighPadding, top = SmallPadding, bottom = SmallPadding)
                          .size(DetailIcon)
                          .constrainAs(iconLike) {
                            start.linkTo(textComment.end)
                            top.linkTo(iconComment.top)
                          },
              )
              Text(
                  text = community.description.toString(),
                  overflow = TextOverflow.Ellipsis,
                  maxLines = 1,
                  style = MovieDetailItemTextStyle,
                  color = Color.Red,
                  modifier =
                      Modifier.padding(
                              start = NormalPadding, top = SmallPadding, bottom = SmallPadding)
                          .constrainAs(textLike) {
                            start.linkTo(iconLike.end)
                            top.linkTo(iconLike.top)
                            bottom.linkTo(iconLike.bottom)
                          })
            }
      }
}

@Preview
@Composable
private fun MovieItemPreview() {
  CommunityItem(
      community =
          CommunityUiModel(
              id = 0,
              name = "Title",
              description = "description",
              dateCreated = "dateCreated",
              dateUpdated = "dateUpdated"),
      onShowDetail = {})
}
