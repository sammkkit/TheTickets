package com.samapp.thetickets.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFA1B3B1),
    onPrimary = Color(0xFF0D1C1B),
    primaryContainer = Color(0xFF2B3C3B),
    onPrimaryContainer = Color.White,

    secondary = Color(0xFFF3E4BE),
    onSecondary = Color(0xFF3C2F04),
    secondaryContainer = Color(0xFF4A3E1A),
    onSecondaryContainer = Color.White,

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,

    outline = Color(0xFF505050),

    error = Color(0xFFCF6679),
    onError = Color.Black,
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color.White
)

val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    tertiary = Tertiary,
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    error = Error,
    onError = OnError
)



@Composable
fun TheTicketsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}