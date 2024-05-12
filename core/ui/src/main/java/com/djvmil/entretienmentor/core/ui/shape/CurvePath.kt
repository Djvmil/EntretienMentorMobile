package com.djvmil.entretienmentor.core.ui.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// https://github.com/aqua30/CustomLoginDesign/
fun ltrCurve(size: Size) =
    Path().apply {
      reset()
      val width = size.width
      val height = size.height
      val radius = 100f
      val upShift = height * (1f - 0.1f)
      arcTo(
          rect = Rect(left = 0f, top = 0f, right = radius * 2, bottom = radius * 2),
          startAngleDegrees = 180f,
          sweepAngleDegrees = 90f,
          forceMoveTo = false)
      lineTo(width - radius, 0f)
      arcTo(
          rect = Rect(left = width - radius * 2, top = 0f, right = width, bottom = radius * 2),
          startAngleDegrees = 270f,
          sweepAngleDegrees = 90f,
          forceMoveTo = false)
      lineTo(width, height - (radius * 2))
      arcTo(
          rect =
              Rect(
                  left = width - radius * 2,
                  top = height - (radius * 2),
                  right = width,
                  bottom = height),
          startAngleDegrees = 0f,
          sweepAngleDegrees = 115f,
          forceMoveTo = false)
      arcTo(
          rect = Rect(left = 0f, top = upShift - radius * 2, right = radius * 2, bottom = upShift),
          startAngleDegrees = 115f,
          sweepAngleDegrees = 65f,
          forceMoveTo = false)
    }

fun rtlCurve(size: Size) =
    Path().apply {
      reset()
      val width = size.width
      val height = size.height
      val radius = 100f
      val upShift = height * (1f - 0.6f)
      arcTo(
          rect = Rect(left = 0f, top = 0f, right = radius * 2, bottom = radius * 2),
          startAngleDegrees = 180f,
          sweepAngleDegrees = 110f,
          forceMoveTo = false)
      arcTo(
          rect =
              Rect(
                  left = width - radius * 2,
                  top = upShift - 10,
                  right = width,
                  bottom = upShift + radius * 2),
          startAngleDegrees = -60f,
          sweepAngleDegrees = 65f,
          forceMoveTo = false)
      arcTo(
          rect =
              Rect(
                  left = width - radius * 2,
                  top = height - radius * 2,
                  right = width,
                  bottom = height),
          startAngleDegrees = 0f,
          sweepAngleDegrees = 90f,
          forceMoveTo = false)
      arcTo(
          rect = Rect(left = 0f, top = height - radius * 2, right = radius * 2, bottom = height),
          startAngleDegrees = 90f,
          sweepAngleDegrees = 90f,
          forceMoveTo = false)
    }

@Composable
fun CurvePath(modifier: Modifier) {
  var text by remember { mutableStateOf("") }

  Column(Modifier.fillMaxSize()) {
    Box(
        modifier =
            modifier
                .padding(30.dp)
                .fillMaxSize()
                .graphicsLayer {
                  shape = CurvedShape(CurveType.LTR)
                  clip = true
                }
                .background(MaterialTheme.colorScheme.secondary)) {}
  }
}

@Preview(showBackground = true)
@Composable
fun CurvePathPreview() {
  CurvePath(Modifier)
}
