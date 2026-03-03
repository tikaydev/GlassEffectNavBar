package com.tikaydev.glasseffect.core.designsystem.provider

import androidx.compose.runtime.compositionLocalOf
import dev.chrisbanes.haze.HazeState

val LocalHazeStateProvider = compositionLocalOf<HazeState> {
    throw IllegalArgumentException("No HazeState found")
}