package com.example.nfccard.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor
)

private val DarkColorPalette = darkColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
