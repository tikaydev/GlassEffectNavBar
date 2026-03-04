package com.tikaydev.glasseffect.core.designsystem.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey


@Serializable
data object HomeRoute : Route, NavKey {

    @Serializable
    data object ImageListRoute : Route, NavKey

    @Serializable
    data class ImageDetailsRoute(
        val imageId: Int,
    ) : Route, NavKey
}

@Serializable
data object MenuRoute : Route, NavKey

@Serializable
data object ProfileRoute : Route, NavKey

@Serializable
data object SettingsRoute: Route, NavKey
