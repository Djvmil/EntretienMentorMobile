package com.djvmil.entretienmentor.core.ui.shape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

// https://proandroiddev.com/custom-layout-designing-in-jetpack-compose-5abbccc74ebd
// https://github.com/aqua30/CustomLoginDesign/

@Composable
fun Modifier.placeAt(
    x: Int,
    y: Int,
) = layout { measurables, constraints ->
  val placeable = measurables.measure(constraints)
  layout(placeable.width, placeable.height) { placeable.placeRelative(x, y) }
}
