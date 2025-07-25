package com.example.unitest.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.unitest.backgroundDark
import com.example.unitest.backgroundDarkHighContrast
import com.example.unitest.backgroundDarkMediumContrast
import com.example.unitest.backgroundLight
import com.example.unitest.backgroundLightHighContrast
import com.example.unitest.backgroundLightMediumContrast
import com.example.unitest.errorContainerDark
import com.example.unitest.errorContainerDarkHighContrast
import com.example.unitest.errorContainerDarkMediumContrast
import com.example.unitest.errorContainerLight
import com.example.unitest.errorContainerLightHighContrast
import com.example.unitest.errorContainerLightMediumContrast
import com.example.unitest.errorDark
import com.example.unitest.errorDarkHighContrast
import com.example.unitest.errorDarkMediumContrast
import com.example.unitest.errorLight
import com.example.unitest.errorLightHighContrast
import com.example.unitest.errorLightMediumContrast
import com.example.unitest.inverseOnSurfaceDark
import com.example.unitest.inverseOnSurfaceDarkHighContrast
import com.example.unitest.inverseOnSurfaceDarkMediumContrast
import com.example.unitest.inverseOnSurfaceLight
import com.example.unitest.inverseOnSurfaceLightHighContrast
import com.example.unitest.inverseOnSurfaceLightMediumContrast
import com.example.unitest.inversePrimaryDark
import com.example.unitest.inversePrimaryDarkHighContrast
import com.example.unitest.inversePrimaryDarkMediumContrast
import com.example.unitest.inversePrimaryLight
import com.example.unitest.inversePrimaryLightHighContrast
import com.example.unitest.inversePrimaryLightMediumContrast
import com.example.unitest.inverseSurfaceDark
import com.example.unitest.inverseSurfaceDarkHighContrast
import com.example.unitest.inverseSurfaceDarkMediumContrast
import com.example.unitest.inverseSurfaceLight
import com.example.unitest.inverseSurfaceLightHighContrast
import com.example.unitest.inverseSurfaceLightMediumContrast
import com.example.unitest.onBackgroundDark
import com.example.unitest.onBackgroundDarkHighContrast
import com.example.unitest.onBackgroundDarkMediumContrast
import com.example.unitest.onBackgroundLight
import com.example.unitest.onBackgroundLightHighContrast
import com.example.unitest.onBackgroundLightMediumContrast
import com.example.unitest.onErrorContainerDark
import com.example.unitest.onErrorContainerDarkHighContrast
import com.example.unitest.onErrorContainerDarkMediumContrast
import com.example.unitest.onErrorContainerLight
import com.example.unitest.onErrorContainerLightHighContrast
import com.example.unitest.onErrorContainerLightMediumContrast
import com.example.unitest.onErrorDark
import com.example.unitest.onErrorDarkHighContrast
import com.example.unitest.onErrorDarkMediumContrast
import com.example.unitest.onErrorLight
import com.example.unitest.onErrorLightHighContrast
import com.example.unitest.onErrorLightMediumContrast
import com.example.unitest.onPrimaryContainerDark
import com.example.unitest.onPrimaryContainerDarkHighContrast
import com.example.unitest.onPrimaryContainerDarkMediumContrast
import com.example.unitest.onPrimaryContainerLight
import com.example.unitest.onPrimaryContainerLightHighContrast
import com.example.unitest.onPrimaryContainerLightMediumContrast
import com.example.unitest.onPrimaryDark
import com.example.unitest.onPrimaryDarkHighContrast
import com.example.unitest.onPrimaryDarkMediumContrast
import com.example.unitest.onPrimaryLight
import com.example.unitest.onPrimaryLightHighContrast
import com.example.unitest.onPrimaryLightMediumContrast
import com.example.unitest.onSecondaryContainerDark
import com.example.unitest.onSecondaryContainerDarkHighContrast
import com.example.unitest.onSecondaryContainerDarkMediumContrast
import com.example.unitest.onSecondaryContainerLight
import com.example.unitest.onSecondaryContainerLightHighContrast
import com.example.unitest.onSecondaryContainerLightMediumContrast
import com.example.unitest.onSecondaryDark
import com.example.unitest.onSecondaryDarkHighContrast
import com.example.unitest.onSecondaryDarkMediumContrast
import com.example.unitest.onSecondaryLight
import com.example.unitest.onSecondaryLightHighContrast
import com.example.unitest.onSecondaryLightMediumContrast
import com.example.unitest.onSurfaceDark
import com.example.unitest.onSurfaceDarkHighContrast
import com.example.unitest.onSurfaceDarkMediumContrast
import com.example.unitest.onSurfaceLight
import com.example.unitest.onSurfaceLightHighContrast
import com.example.unitest.onSurfaceLightMediumContrast
import com.example.unitest.onSurfaceVariantDark
import com.example.unitest.onSurfaceVariantDarkHighContrast
import com.example.unitest.onSurfaceVariantDarkMediumContrast
import com.example.unitest.onSurfaceVariantLight
import com.example.unitest.onSurfaceVariantLightHighContrast
import com.example.unitest.onSurfaceVariantLightMediumContrast
import com.example.unitest.onTertiaryContainerDark
import com.example.unitest.onTertiaryContainerDarkHighContrast
import com.example.unitest.onTertiaryContainerDarkMediumContrast
import com.example.unitest.onTertiaryContainerLight
import com.example.unitest.onTertiaryContainerLightHighContrast
import com.example.unitest.onTertiaryContainerLightMediumContrast
import com.example.unitest.onTertiaryDark
import com.example.unitest.onTertiaryDarkHighContrast
import com.example.unitest.onTertiaryDarkMediumContrast
import com.example.unitest.onTertiaryLight
import com.example.unitest.onTertiaryLightHighContrast
import com.example.unitest.onTertiaryLightMediumContrast
import com.example.unitest.outlineDark
import com.example.unitest.outlineDarkHighContrast
import com.example.unitest.outlineDarkMediumContrast
import com.example.unitest.outlineLight
import com.example.unitest.outlineLightHighContrast
import com.example.unitest.outlineLightMediumContrast
import com.example.unitest.outlineVariantDark
import com.example.unitest.outlineVariantDarkHighContrast
import com.example.unitest.outlineVariantDarkMediumContrast
import com.example.unitest.outlineVariantLight
import com.example.unitest.outlineVariantLightHighContrast
import com.example.unitest.outlineVariantLightMediumContrast
import com.example.unitest.primaryContainerDark
import com.example.unitest.primaryContainerDarkHighContrast
import com.example.unitest.primaryContainerDarkMediumContrast
import com.example.unitest.primaryContainerLight
import com.example.unitest.primaryContainerLightHighContrast
import com.example.unitest.primaryContainerLightMediumContrast
import com.example.unitest.primaryDark
import com.example.unitest.primaryDarkHighContrast
import com.example.unitest.primaryDarkMediumContrast
import com.example.unitest.primaryLight
import com.example.unitest.primaryLightHighContrast
import com.example.unitest.primaryLightMediumContrast
import com.example.unitest.scrimDark
import com.example.unitest.scrimDarkHighContrast
import com.example.unitest.scrimDarkMediumContrast
import com.example.unitest.scrimLight
import com.example.unitest.scrimLightHighContrast
import com.example.unitest.scrimLightMediumContrast
import com.example.unitest.secondaryContainerDark
import com.example.unitest.secondaryContainerDarkHighContrast
import com.example.unitest.secondaryContainerDarkMediumContrast
import com.example.unitest.secondaryContainerLight
import com.example.unitest.secondaryContainerLightHighContrast
import com.example.unitest.secondaryContainerLightMediumContrast
import com.example.unitest.secondaryDark
import com.example.unitest.secondaryDarkHighContrast
import com.example.unitest.secondaryDarkMediumContrast
import com.example.unitest.secondaryLight
import com.example.unitest.secondaryLightHighContrast
import com.example.unitest.secondaryLightMediumContrast
import com.example.unitest.surfaceBrightDark
import com.example.unitest.surfaceBrightDarkHighContrast
import com.example.unitest.surfaceBrightDarkMediumContrast
import com.example.unitest.surfaceBrightLight
import com.example.unitest.surfaceBrightLightHighContrast
import com.example.unitest.surfaceBrightLightMediumContrast
import com.example.unitest.surfaceContainerDark
import com.example.unitest.surfaceContainerDarkHighContrast
import com.example.unitest.surfaceContainerDarkMediumContrast
import com.example.unitest.surfaceContainerHighDark
import com.example.unitest.surfaceContainerHighDarkHighContrast
import com.example.unitest.surfaceContainerHighDarkMediumContrast
import com.example.unitest.surfaceContainerHighLight
import com.example.unitest.surfaceContainerHighLightHighContrast
import com.example.unitest.surfaceContainerHighLightMediumContrast
import com.example.unitest.surfaceContainerHighestDark
import com.example.unitest.surfaceContainerHighestDarkHighContrast
import com.example.unitest.surfaceContainerHighestDarkMediumContrast
import com.example.unitest.surfaceContainerHighestLight
import com.example.unitest.surfaceContainerHighestLightHighContrast
import com.example.unitest.surfaceContainerHighestLightMediumContrast
import com.example.unitest.surfaceContainerLight
import com.example.unitest.surfaceContainerLightHighContrast
import com.example.unitest.surfaceContainerLightMediumContrast
import com.example.unitest.surfaceContainerLowDark
import com.example.unitest.surfaceContainerLowDarkHighContrast
import com.example.unitest.surfaceContainerLowDarkMediumContrast
import com.example.unitest.surfaceContainerLowLight
import com.example.unitest.surfaceContainerLowLightHighContrast
import com.example.unitest.surfaceContainerLowLightMediumContrast
import com.example.unitest.surfaceContainerLowestDark
import com.example.unitest.surfaceContainerLowestDarkHighContrast
import com.example.unitest.surfaceContainerLowestDarkMediumContrast
import com.example.unitest.surfaceContainerLowestLight
import com.example.unitest.surfaceContainerLowestLightHighContrast
import com.example.unitest.surfaceContainerLowestLightMediumContrast
import com.example.unitest.surfaceDark
import com.example.unitest.surfaceDarkHighContrast
import com.example.unitest.surfaceDarkMediumContrast
import com.example.unitest.surfaceDimDark
import com.example.unitest.surfaceDimDarkHighContrast
import com.example.unitest.surfaceDimDarkMediumContrast
import com.example.unitest.surfaceDimLight
import com.example.unitest.surfaceDimLightHighContrast
import com.example.unitest.surfaceDimLightMediumContrast
import com.example.unitest.surfaceLight
import com.example.unitest.surfaceLightHighContrast
import com.example.unitest.surfaceLightMediumContrast
import com.example.unitest.surfaceVariantDark
import com.example.unitest.surfaceVariantDarkHighContrast
import com.example.unitest.surfaceVariantDarkMediumContrast
import com.example.unitest.surfaceVariantLight
import com.example.unitest.surfaceVariantLightHighContrast
import com.example.unitest.surfaceVariantLightMediumContrast
import com.example.unitest.tertiaryContainerDark
import com.example.unitest.tertiaryContainerDarkHighContrast
import com.example.unitest.tertiaryContainerDarkMediumContrast
import com.example.unitest.tertiaryContainerLight
import com.example.unitest.tertiaryContainerLightHighContrast
import com.example.unitest.tertiaryContainerLightMediumContrast
import com.example.unitest.tertiaryDark
import com.example.unitest.tertiaryDarkHighContrast
import com.example.unitest.tertiaryDarkMediumContrast
import com.example.unitest.tertiaryLight
import com.example.unitest.tertiaryLightHighContrast
import com.example.unitest.tertiaryLightMediumContrast

@Composable
fun UniTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)
