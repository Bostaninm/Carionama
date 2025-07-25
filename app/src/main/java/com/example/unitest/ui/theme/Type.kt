package com.example.unitest.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.unitest.R

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//)


val RobotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_extra_bold, FontWeight.ExtraBold),
    Font(R.font.roboto_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.roboto_extra_light, FontWeight.ExtraLight),
    Font(R.font.roboto_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.roboto_semi_bold, FontWeight.SemiBold),
    Font(R.font.roboto_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_thin_italic, FontWeight.Thin, FontStyle.Italic),
    // Add other styles like Black, Thin if you've included them
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = RobotoFamily, // Use RobotoFamily
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal, // Or FontWeight.Medium for titles
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Medium, // You used labelSmall in Select.kt
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
//val baseline = Typography()
//val AppTypography = Typography(
//    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
//    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
//    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
//    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
//    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
//    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
//    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
//    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
//    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
//    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
//    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
//    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
//    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
//    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
//    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
//)
