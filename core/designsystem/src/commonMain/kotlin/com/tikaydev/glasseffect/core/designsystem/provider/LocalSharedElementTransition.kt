/*
 * Copyright (C) 2024 Homi. All rights reserved.
 * Homi Android Team <developers@homi>
 * Created by Alex Tenkorang
 */
package com.tikaydev.glasseffect.core.designsystem.provider

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.compositionLocalOf

val LocalAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope> {
    throw IllegalArgumentException("No AnimatedVisibilityScope found")
}

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope> {
    throw IllegalArgumentException("No SharedTransitionScope found")
}
