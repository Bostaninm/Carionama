package com.example.carionama.theme

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
import com.example.carionama.backgroundDark
import com.example.carionama.backgroundDarkHighContrast
import com.example.carionama.backgroundDarkMediumContrast
import com.example.carionama.backgroundLight
import com.example.carionama.backgroundLightHighContrast
import com.example.carionama.backgroundLightMediumContrast
import com.example.carionama.errorContainerDark
import com.example.carionama.errorContainerDarkHighContrast
import com.example.carionama.errorContainerDarkMediumContrast
import com.example.carionama.errorContainerLight
import com.example.carionama.errorContainerLightHighContrast
import com.example.carionama.errorContainerLightMediumContrast
import com.example.carionama.errorDark
import com.example.carionama.errorDarkHighContrast
import com.example.carionama.errorDarkMediumContrast
import com.example.carionama.errorLight
import com.example.carionama.errorLightHighContrast
import com.example.carionama.errorLightMediumContrast
import com.example.carionama.inverseOnSurfaceDark
import com.example.carionama.inverseOnSurfaceDarkHighContrast
import com.example.carionama.inverseOnSurfaceDarkMediumContrast
import com.example.carionama.inverseOnSurfaceLight
import com.example.carionama.inverseOnSurfaceLightHighContrast
import com.example.carionama.inverseOnSurfaceLightMediumContrast
import com.example.carionama.inversePrimaryDark
import com.example.carionama.inversePrimaryDarkHighContrast
import com.example.carionama.inversePrimaryDarkMediumContrast
import com.example.carionama.inversePrimaryLight
import com.example.carionama.inversePrimaryLightHighContrast
import com.example.carionama.inversePrimaryLightMediumContrast
import com.example.carionama.inverseSurfaceDark
import com.example.carionama.inverseSurfaceDarkHighContrast
import com.example.carionama.inverseSurfaceDarkMediumContrast
import com.example.carionama.inverseSurfaceLight
import com.example.carionama.inverseSurfaceLightHighContrast
import com.example.carionama.inverseSurfaceLightMediumContrast
import com.example.carionama.onBackgroundDark
import com.example.carionama.onBackgroundDarkHighContrast
import com.example.carionama.onBackgroundDarkMediumContrast
import com.example.carionama.onBackgroundLight
import com.example.carionama.onBackgroundLightHighContrast
import com.example.carionama.onBackgroundLightMediumContrast
import com.example.carionama.onErrorContainerDark
import com.example.carionama.onErrorContainerDarkHighContrast
import com.example.carionama.onErrorContainerDarkMediumContrast
import com.example.carionama.onErrorContainerLight
import com.example.carionama.onErrorContainerLightHighContrast
import com.example.carionama.onErrorContainerLightMediumContrast
import com.example.carionama.onErrorDark
import com.example.carionama.onErrorDarkHighContrast
import com.example.carionama.onErrorDarkMediumContrast
import com.example.carionama.onErrorLight
import com.example.carionama.onErrorLightHighContrast
import com.example.carionama.onErrorLightMediumContrast
import com.example.carionama.onPrimaryContainerDark
import com.example.carionama.onPrimaryContainerDarkHighContrast
import com.example.carionama.onPrimaryContainerDarkMediumContrast
import com.example.carionama.onPrimaryContainerLight
import com.example.carionama.onPrimaryContainerLightHighContrast
import com.example.carionama.onPrimaryContainerLightMediumContrast
import com.example.carionama.onPrimaryDark
import com.example.carionama.onPrimaryDarkHighContrast
import com.example.carionama.onPrimaryDarkMediumContrast
import com.example.carionama.onPrimaryLight
import com.example.carionama.onPrimaryLightHighContrast
import com.example.carionama.onPrimaryLightMediumContrast
import com.example.carionama.onSecondaryContainerDark
import com.example.carionama.onSecondaryContainerDarkHighContrast
import com.example.carionama.onSecondaryContainerDarkMediumContrast
import com.example.carionama.onSecondaryContainerLight
import com.example.carionama.onSecondaryContainerLightHighContrast
import com.example.carionama.onSecondaryContainerLightMediumContrast
import com.example.carionama.onSecondaryDark
import com.example.carionama.onSecondaryDarkHighContrast
import com.example.carionama.onSecondaryDarkMediumContrast
import com.example.carionama.onSecondaryLight
import com.example.carionama.onSecondaryLightHighContrast
import com.example.carionama.onSecondaryLightMediumContrast
import com.example.carionama.onSurfaceDark
import com.example.carionama.onSurfaceDarkHighContrast
import com.example.carionama.onSurfaceDarkMediumContrast
import com.example.carionama.onSurfaceLight
import com.example.carionama.onSurfaceLightHighContrast
import com.example.carionama.onSurfaceLightMediumContrast
import com.example.carionama.onSurfaceVariantDark
import com.example.carionama.onSurfaceVariantDarkHighContrast
import com.example.carionama.onSurfaceVariantDarkMediumContrast
import com.example.carionama.onSurfaceVariantLight
import com.example.carionama.onSurfaceVariantLightHighContrast
import com.example.carionama.onSurfaceVariantLightMediumContrast
import com.example.carionama.onTertiaryContainerDark
import com.example.carionama.onTertiaryContainerDarkHighContrast
import com.example.carionama.onTertiaryContainerDarkMediumContrast
import com.example.carionama.onTertiaryContainerLight
import com.example.carionama.onTertiaryContainerLightHighContrast
import com.example.carionama.onTertiaryContainerLightMediumContrast
import com.example.carionama.onTertiaryDark
import com.example.carionama.onTertiaryDarkHighContrast
import com.example.carionama.onTertiaryDarkMediumContrast
import com.example.carionama.onTertiaryLight
import com.example.carionama.onTertiaryLightHighContrast
import com.example.carionama.onTertiaryLightMediumContrast
import com.example.carionama.outlineDark
import com.example.carionama.outlineDarkHighContrast
import com.example.carionama.outlineDarkMediumContrast
import com.example.carionama.outlineLight
import com.example.carionama.outlineLightHighContrast
import com.example.carionama.outlineLightMediumContrast
import com.example.carionama.outlineVariantDark
import com.example.carionama.outlineVariantDarkHighContrast
import com.example.carionama.outlineVariantDarkMediumContrast
import com.example.carionama.outlineVariantLight
import com.example.carionama.outlineVariantLightHighContrast
import com.example.carionama.outlineVariantLightMediumContrast
import com.example.carionama.primaryContainerDark
import com.example.carionama.primaryContainerDarkHighContrast
import com.example.carionama.primaryContainerDarkMediumContrast
import com.example.carionama.primaryContainerLight
import com.example.carionama.primaryContainerLightHighContrast
import com.example.carionama.primaryContainerLightMediumContrast
import com.example.carionama.primaryDark
import com.example.carionama.primaryDarkHighContrast
import com.example.carionama.primaryDarkMediumContrast
import com.example.carionama.primaryLight
import com.example.carionama.primaryLightHighContrast
import com.example.carionama.primaryLightMediumContrast
import com.example.carionama.scrimDark
import com.example.carionama.scrimDarkHighContrast
import com.example.carionama.scrimDarkMediumContrast
import com.example.carionama.scrimLight
import com.example.carionama.scrimLightHighContrast
import com.example.carionama.scrimLightMediumContrast
import com.example.carionama.secondaryContainerDark
import com.example.carionama.secondaryContainerDarkHighContrast
import com.example.carionama.secondaryContainerDarkMediumContrast
import com.example.carionama.secondaryContainerLight
import com.example.carionama.secondaryContainerLightHighContrast
import com.example.carionama.secondaryContainerLightMediumContrast
import com.example.carionama.secondaryDark
import com.example.carionama.secondaryDarkHighContrast
import com.example.carionama.secondaryDarkMediumContrast
import com.example.carionama.secondaryLight
import com.example.carionama.secondaryLightHighContrast
import com.example.carionama.secondaryLightMediumContrast
import com.example.carionama.surfaceBrightDark
import com.example.carionama.surfaceBrightDarkHighContrast
import com.example.carionama.surfaceBrightDarkMediumContrast
import com.example.carionama.surfaceBrightLight
import com.example.carionama.surfaceBrightLightHighContrast
import com.example.carionama.surfaceBrightLightMediumContrast
import com.example.carionama.surfaceContainerDark
import com.example.carionama.surfaceContainerDarkHighContrast
import com.example.carionama.surfaceContainerDarkMediumContrast
import com.example.carionama.surfaceContainerHighDark
import com.example.carionama.surfaceContainerHighDarkHighContrast
import com.example.carionama.surfaceContainerHighDarkMediumContrast
import com.example.carionama.surfaceContainerHighLight
import com.example.carionama.surfaceContainerHighLightHighContrast
import com.example.carionama.surfaceContainerHighLightMediumContrast
import com.example.carionama.surfaceContainerHighestDark
import com.example.carionama.surfaceContainerHighestDarkHighContrast
import com.example.carionama.surfaceContainerHighestDarkMediumContrast
import com.example.carionama.surfaceContainerHighestLight
import com.example.carionama.surfaceContainerHighestLightHighContrast
import com.example.carionama.surfaceContainerHighestLightMediumContrast
import com.example.carionama.surfaceContainerLight
import com.example.carionama.surfaceContainerLightHighContrast
import com.example.carionama.surfaceContainerLightMediumContrast
import com.example.carionama.surfaceContainerLowDark
import com.example.carionama.surfaceContainerLowDarkHighContrast
import com.example.carionama.surfaceContainerLowDarkMediumContrast
import com.example.carionama.surfaceContainerLowLight
import com.example.carionama.surfaceContainerLowLightHighContrast
import com.example.carionama.surfaceContainerLowLightMediumContrast
import com.example.carionama.surfaceContainerLowestDark
import com.example.carionama.surfaceContainerLowestDarkHighContrast
import com.example.carionama.surfaceContainerLowestDarkMediumContrast
import com.example.carionama.surfaceContainerLowestLight
import com.example.carionama.surfaceContainerLowestLightHighContrast
import com.example.carionama.surfaceContainerLowestLightMediumContrast
import com.example.carionama.surfaceDark
import com.example.carionama.surfaceDarkHighContrast
import com.example.carionama.surfaceDarkMediumContrast
import com.example.carionama.surfaceDimDark
import com.example.carionama.surfaceDimDarkHighContrast
import com.example.carionama.surfaceDimDarkMediumContrast
import com.example.carionama.surfaceDimLight
import com.example.carionama.surfaceDimLightHighContrast
import com.example.carionama.surfaceDimLightMediumContrast
import com.example.carionama.surfaceLight
import com.example.carionama.surfaceLightHighContrast
import com.example.carionama.surfaceLightMediumContrast
import com.example.carionama.surfaceVariantDark
import com.example.carionama.surfaceVariantDarkHighContrast
import com.example.carionama.surfaceVariantDarkMediumContrast
import com.example.carionama.surfaceVariantLight
import com.example.carionama.surfaceVariantLightHighContrast
import com.example.carionama.surfaceVariantLightMediumContrast
import com.example.carionama.tertiaryContainerDark
import com.example.carionama.tertiaryContainerDarkHighContrast
import com.example.carionama.tertiaryContainerDarkMediumContrast
import com.example.carionama.tertiaryContainerLight
import com.example.carionama.tertiaryContainerLightHighContrast
import com.example.carionama.tertiaryContainerLightMediumContrast
import com.example.carionama.tertiaryDark
import com.example.carionama.tertiaryDarkHighContrast
import com.example.carionama.tertiaryDarkMediumContrast
import com.example.carionama.tertiaryLight
import com.example.carionama.tertiaryLightHighContrast
import com.example.carionama.tertiaryLightMediumContrast

@Composable
fun CarionamaTheme(
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
