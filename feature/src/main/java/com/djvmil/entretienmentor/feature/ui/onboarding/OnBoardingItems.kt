package com.djvmil.entretienmentor.feature.ui.onboarding

import com.djvmil.entretienmentor.feature.R

class OnBoardingItems(val image: Int, val title: Int, val desc: Int) {
  companion object {
    fun getData(): List<OnBoardingItems> {
      return listOf(
          OnBoardingItems(
              R.drawable.polygon_background, R.string.onBoardingTitle1, R.string.onBoardingText1),
          OnBoardingItems(
              R.drawable.polygon_background, R.string.onBoardingTitle2, R.string.onBoardingText2),
          OnBoardingItems(
              R.drawable.polygon_background, R.string.onBoardingTitle3, R.string.onBoardingText3))
    }
  }
}
