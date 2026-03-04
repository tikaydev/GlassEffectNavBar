package com.tikaydev.glasseffect.core.designsystem.provider

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf


data class ScreenSize(
    val isLargeScreen: Boolean = false,
)

/**
 * CompositionLocal to track if the app should use a large screen layout (NavRail)
 * or a compact layout (BottomBar).
 */
val LocalScreenSizeProvider = compositionLocalOf { ScreenSize() }

val MaterialTheme.screenSize: ScreenSize
    @Composable
    @ReadOnlyComposable
    get() = LocalScreenSizeProvider.current


