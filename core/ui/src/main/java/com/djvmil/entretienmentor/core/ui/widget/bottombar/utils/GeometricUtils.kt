package com.djvmil.entretienmentor.core.ui.widget.bottombar.utils

fun lerp(start: Float, stop: Float, fraction: Float) = (start * (1 - fraction) + stop * fraction)
