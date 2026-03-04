package com.tikaydev.glasseffect.core.designsystem.provider

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

data class DarkTheme(
    val isDark: Boolean = false,
)

val LocalDarkTheme = compositionLocalOf { DarkTheme() }

val MaterialTheme.darkTheme: DarkTheme
    @Composable
    @ReadOnlyComposable
    get() = LocalDarkTheme.current