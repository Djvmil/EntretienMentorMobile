package com.djvmil.entretienmentor.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.djvmil.entretienmentor.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /*body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))
    ),
    subtitle1 = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
    ),
    subtitle2 = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
    )*/
)