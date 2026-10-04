package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NeighborknotLightColorScheme = lightColorScheme(
    primary = PrimarySage,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = SecondaryTerracottaVibrant,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = TertiaryAmberWarm,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    background = CeramicLinen,
    onBackground = SlateInk,
    surface = SurfaceLinen,
    onSurface = SlateInk,
    surfaceVariant = SurfaceContainerHighest,
    onSurfaceVariant = SlateInkVariant,
    outline = OutlineStone,
    outlineVariant = OutlineVariantStone,
    error = ErrorRed,
    onError = OnError,
    errorContainer = ErrorContainer
)

private val NeighborknotDarkColorScheme = darkColorScheme(
    primary = PrimaryFixedDim,
    onPrimary = PrimarySage,
    primaryContainer = PrimarySageMedium,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = SecondaryContainer,
    onSecondary = OnSecondaryContainer,
    secondaryContainer = SecondaryTerracotta,
    onSecondaryContainer = TerracottaTint,
    tertiary = TertiaryAmberWarm,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    background = Color(0xFF191815),
    onBackground = CeramicLinen,
    surface = Color(0xFF22201C),
    onSurface = CeramicLinen,
    surfaceVariant = Color(0xFF33302A),
    onSurfaceVariant = Color(0xFFC7C3BA),
    outline = OutlineVariantStone,
    outlineVariant = OutlineStone,
    error = ErrorRed,
    onError = OnError,
    errorContainer = ErrorContainer
)

@Composable
fun NeighborknotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) NeighborknotDarkColorScheme else NeighborknotLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
