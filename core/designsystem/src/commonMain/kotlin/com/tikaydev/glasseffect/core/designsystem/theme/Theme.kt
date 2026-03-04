package com.tikaydev.glasseffect.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.window.core.layout.WindowSizeClass
import com.materialkolor.DynamicMaterialExpressiveTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicMaterialThemeState
import com.tikaydev.glasseffect.core.designsystem.provider.DarkTheme
import com.tikaydev.glasseffect.core.designsystem.provider.LocalDarkTheme
import com.tikaydev.glasseffect.core.designsystem.provider.LocalScreenSizeProvider
import com.tikaydev.glasseffect.core.designsystem.provider.ScreenSize

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isLargeScreen = windowSizeClass.isWidthAtLeastBreakpoint(
        WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND
    )
//    val seedColor = Color(0xFFFF942D)
    val seedColor = Color.Cyan
    val dynamicThemeState = rememberDynamicMaterialThemeState(
        isDark = isDarkTheme,
        style = PaletteStyle.TonalSpot,
        seedColor = seedColor,
    )

    val screenSize = ScreenSize(isLargeScreen)
    val localDarkTheme = DarkTheme(isDarkTheme)
    CompositionLocalProvider(
        LocalDarkTheme provides localDarkTheme,
        LocalScreenSizeProvider provides screenSize
    ) {
        DynamicMaterialExpressiveTheme(
            state = dynamicThemeState,
            motionScheme = MotionScheme.expressive(),
            animate = true,
            content = content,
        )
    }
}
