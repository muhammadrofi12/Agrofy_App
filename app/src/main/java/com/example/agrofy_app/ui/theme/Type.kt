package com.example.agrofy_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.agrofy_app.R

val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold)
)

// Default Material Typography
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    displayLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.5).sp
    )
)

// Helper function to create TextStyle with specific FontWeight and size
fun poppinsTextStyle(weight: FontWeight, size: Int, lineHeight: Int, letterSpacing: Float) = TextStyle(
    fontFamily = PoppinsFontFamily,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
    letterSpacing = letterSpacing.sp
)

// Define text styles with different weights and sizes
// Regular
val PoppinsRegular8 = poppinsTextStyle(FontWeight.Normal, 8, 12, 0.1f)
val PoppinsRegular10 = poppinsTextStyle(FontWeight.Normal, 10, 14, 0.1f)
val PoppinsRegular12 = poppinsTextStyle(FontWeight.Normal, 12, 16, 0.1f)
val PoppinsRegular14 = poppinsTextStyle(FontWeight.Normal, 14, 20, 0.1f)
val PoppinsRegular16 = poppinsTextStyle(FontWeight.Normal, 16, 24, 0.1f)
val PoppinsRegular18 = poppinsTextStyle(FontWeight.Normal, 18, 24, 0.1f)
val PoppinsRegular20 = poppinsTextStyle(FontWeight.Normal, 20, 28, 0.1f)
val PoppinsRegular24 = poppinsTextStyle(FontWeight.Normal, 24, 32, 0.1f)
val PoppinsRegular26 = poppinsTextStyle(FontWeight.Normal, 26, 34, 0.1f)

// Bold
val PoppinsBold8 = poppinsTextStyle(FontWeight.Bold, 8, 12, 0.1f)
val PoppinsBold10 = poppinsTextStyle(FontWeight.Bold, 10, 14, 0.1f)
val PoppinsBold12 = poppinsTextStyle(FontWeight.Bold, 12, 16, 0.1f)
val PoppinsBold14 = poppinsTextStyle(FontWeight.Bold, 14, 20, 0.1f)
val PoppinsBold16 = poppinsTextStyle(FontWeight.Bold, 16, 24, 0.1f)
val PoppinsBold18 = poppinsTextStyle(FontWeight.Bold, 18, 24, 0.1f)
val PoppinsBold20 = poppinsTextStyle(FontWeight.Bold, 20, 28, 0.1f)
val PoppinsBold24 = poppinsTextStyle(FontWeight.Bold, 24, 32, 0.1f)
val PoppinsBold26 = poppinsTextStyle(FontWeight.Bold, 26, 34, 0.1f)

// Medium
val PoppinsMedium8 = poppinsTextStyle(FontWeight.Medium, 8, 12, 0.1f)
val PoppinsMedium10 = poppinsTextStyle(FontWeight.Medium, 10, 14, 0.1f)
val PoppinsMedium12 = poppinsTextStyle(FontWeight.Medium, 12, 16, 0.1f)
val PoppinsMedium14 = poppinsTextStyle(FontWeight.Medium, 14, 20, 0.1f)
val PoppinsMedium16 = poppinsTextStyle(FontWeight.Medium, 16, 24, 0.1f)
val PoppinsMedium18 = poppinsTextStyle(FontWeight.Medium, 18, 24, 0.1f)
val PoppinsMedium20 = poppinsTextStyle(FontWeight.Medium, 20, 28, 0.1f)
val PoppinsMedium24 = poppinsTextStyle(FontWeight.Medium, 24, 32, 0.1f)
val PoppinsMedium26 = poppinsTextStyle(FontWeight.Medium, 26, 34, 0.1f)

// SemiBold
val PoppinsSemiBold8 = poppinsTextStyle(FontWeight.SemiBold, 8, 12, 0.1f)
val PoppinsSemiBold10 = poppinsTextStyle(FontWeight.SemiBold, 10, 14, 0.1f)
val PoppinsSemiBold12 = poppinsTextStyle(FontWeight.SemiBold, 12, 16, 0.1f)
val PoppinsSemiBold14 = poppinsTextStyle(FontWeight.SemiBold, 14, 20, 0.1f)
val PoppinsSemiBold16 = poppinsTextStyle(FontWeight.SemiBold, 16, 24, 0.1f)
val PoppinsSemiBold18 = poppinsTextStyle(FontWeight.SemiBold, 18, 24, 0.1f)
val PoppinsSemiBold20 = poppinsTextStyle(FontWeight.SemiBold, 20, 28, 0.1f)
val PoppinsSemiBold24 = poppinsTextStyle(FontWeight.SemiBold, 24, 32, 0.1f)
val PoppinsSemiBold26 = poppinsTextStyle(FontWeight.SemiBold, 26, 34, 0.1f)

// ExtraBold
val PoppinsExtraBold8 = poppinsTextStyle(FontWeight.ExtraBold, 8, 12, 0.1f)
val PoppinsExtraBold10 = poppinsTextStyle(FontWeight.ExtraBold, 10, 14, 0.1f)
val PoppinsExtraBold12 = poppinsTextStyle(FontWeight.ExtraBold, 12, 16, 0.1f)
val PoppinsExtraBold14 = poppinsTextStyle(FontWeight.ExtraBold, 14, 20, 0.1f)
val PoppinsExtraBold16 = poppinsTextStyle(FontWeight.ExtraBold, 16, 24, 0.1f)
val PoppinsExtraBold18 = poppinsTextStyle(FontWeight.ExtraBold, 18, 24, 0.1f)
val PoppinsExtraBold20 = poppinsTextStyle(FontWeight.ExtraBold, 20, 28, 0.1f)
val PoppinsExtraBold24 = poppinsTextStyle(FontWeight.ExtraBold, 24, 32, 0.1f)
val PoppinsExtraBold26 = poppinsTextStyle(FontWeight.ExtraBold, 26, 34, 0.1f)