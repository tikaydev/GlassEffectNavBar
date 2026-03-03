/*
 * Copyright (C) 2024 Homi. All rights reserved.
 * Homi Android Team <developers@homi>
 * Created by Alex Tenkorang
 */
package com.tikaydev.glasseffect.core.designsystem.provider

import androidx.compose.runtime.compositionLocalOf

data class NetworkObserver(
    val isConnected: Boolean = false,
)

val LocalNetworkObserver = compositionLocalOf { NetworkObserver() }
