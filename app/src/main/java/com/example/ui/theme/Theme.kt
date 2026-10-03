package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val NutriPulseDarkScheme = darkColorScheme(
    primary = PrimaryEmerald,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryEmerald,
    onPrimaryContainer = OnPrimaryContainer,
    inversePrimary = PrimaryFixedDim,
    secondary = SecondaryAmber,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = TertiaryPurple,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    background = ObsidianBase,
    onBackground = TextPrimary,
    surface = ObsidianBase,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = TextSecondary,
    surfaceContainer = SurfaceContainer,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    surfaceContainerLowest = ObsidianLowest,
    surfaceBright = SurfaceBright,
    outline = TextMuted,
    outlineVariant = OutlineVariant,
    error = ErrorRed,
    onError = OnError,
    errorContainer = ErrorContainer,
)

@Composable
fun NutriPulseTheme(
    darkTheme: Boolean = true, // Defaults to Cyberpunk Obsidian dark theme
    content: @Composable () -> Unit
) {
    val colorScheme = NutriPulseDarkScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = ObsidianBase.toArgb()
                window.navigationBarColor = ObsidianBase.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
