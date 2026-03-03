package com.tikaydev.glasseffect.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.materialkolor.DynamicMaterialExpressiveTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicMaterialThemeState

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val seedColor = Color(0xFFFF942D)
//    val seedColor = Color.Cyan
    val localDarkTheme = DarkTheme(isDarkTheme)
    val dynamicThemeState = rememberDynamicMaterialThemeState(
        isDark = isDarkTheme,
        style = PaletteStyle.TonalSpot,
        seedColor = seedColor,
    )
    CompositionLocalProvider(
        LocalDarkTheme provides localDarkTheme,
    ) {
        DynamicMaterialExpressiveTheme(
            state = dynamicThemeState,
            motionScheme = MotionScheme.expressive(),
            animate = true,
            content = content,
        )
    }
}

data class DarkTheme(
    val isDark: Boolean = false,
)

val LocalDarkTheme = compositionLocalOf { DarkTheme() }

val MaterialTheme.isDarkTheme: DarkTheme
    @Composable
    @ReadOnlyComposable
    get() = LocalDarkTheme.current
