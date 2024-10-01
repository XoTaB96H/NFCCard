package com.example.nfccard.ui.theme


import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext




// Определение светлой и темной цветовых схем для Material 3
private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    onPrimary = OnPrimaryColor,
    primaryContainer = PrimaryContainerColor
)

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    onPrimary = OnPrimaryColor,
    primaryContainer = PrimaryContainerColor
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors, // Используйте colorScheme для Material3
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
