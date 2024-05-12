package com.djvmil.entretienmentor.feature.ui.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.djvmil.entretienmentor.feature.R
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.BellColorButton
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.ButtonBackground
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.CalendarAnimation
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.ColorButtonAnimation
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.GearColorButton
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.PlusColorButton

@Stable
data class Item(
    @DrawableRes val icon: Int,
    var isSelected: Boolean,
    @StringRes val description: Int,
    val animationType: ColorButtonAnimation =
        BellColorButton(tween(500), background = ButtonBackground(R.drawable.plus)),
)

val colorButtons =
    listOf(
        Item(
            icon = R.drawable.outline_home,
            isSelected = true,
            description = R.string.Home,
            animationType =
                BellColorButton(
                    animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
                    background =
                        ButtonBackground(
                            icon = R.drawable.circle_background, offset = DpOffset(2.5.dp, 3.dp)),
                )),
        Item(
            icon = R.drawable.outline_bell,
            isSelected = false,
            description = R.string.Bell,
            animationType =
                BellColorButton(
                    animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
                    background =
                        ButtonBackground(
                            icon = R.drawable.rectangle_background, offset = DpOffset(1.dp, 2.dp)),
                )),
        Item(
            icon = R.drawable.rounded_rect,
            isSelected = false,
            description = R.string.Plus,
            animationType =
                PlusColorButton(
                    animationSpec =
                        spring(dampingRatio = 0.3f, stiffness = Spring.StiffnessVeryLow),
                    background =
                        ButtonBackground(
                            icon = R.drawable.polygon_background, offset = DpOffset(1.6.dp, 2.dp)),
                )),
        Item(
            icon = R.drawable.calendar,
            isSelected = false,
            description = R.string.Calendar,
            animationType =
                CalendarAnimation(
                    animationSpec =
                        spring(dampingRatio = 0.3f, stiffness = Spring.StiffnessVeryLow),
                    background =
                        ButtonBackground(
                            icon = R.drawable.quadrangle_background,
                            offset = DpOffset(1.dp, 1.5.dp)),
                )),
        Item(
            icon = R.drawable.quadrangle_background,
            isSelected = false,
            description = R.string.Settings,
            animationType =
                GearColorButton(
                    animationSpec =
                        spring(dampingRatio = 0.3f, stiffness = Spring.StiffnessVeryLow),
                    background =
                        ButtonBackground(
                            icon = R.drawable.quadrangle_background,
                            offset = DpOffset(2.5.dp, 3.dp)),
                )))
