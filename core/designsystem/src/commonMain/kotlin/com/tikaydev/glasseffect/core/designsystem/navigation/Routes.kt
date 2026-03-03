package com.tikaydev.glasseffect.core.designsystem.navigation

import kotlinx.serialization.Serializable

sealed interface Route


@Serializable
data object HomeRoute : Route {

    @Serializable
    data object ImageListRoute : Route

    @Serializable
    data class ImageDetailsRoute(
        val imageId: Int,
    ) : Route
}

@Serializable
data object MenuRoute : Route

@Serializable
data object ProfileRoute : Route

@Serializable
data object SettingsRoute: Route
