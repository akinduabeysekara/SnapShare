package com.example.snapshare.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val NightScheme = darkColorScheme(
    primary = NightPrimary,
    secondary = NightSecondary,
    tertiary = NightTertiary
)

private val DayScheme = lightColorScheme(
    primary = DayPrimary,
    secondary = DaySecondary,
    tertiary = DayTertiary
)

@Composable
fun SnapShareTheme(
    useNightColors: Boolean = isSystemInDarkTheme(),
    useWallpaperColors: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = resolveColorScheme(useNightColors, useWallpaperColors),
        typography = AppTypography,
        content = content
    )
}

@Composable
private fun resolveColorScheme(night: Boolean, wallpaper: Boolean): ColorScheme {
    // Wallpaper-derived color is only available on Android 12+.
    if (wallpaper && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        return if (night) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    return if (night) NightScheme else DayScheme
}
