package com.tikaydev.glasseffect.feature.home.navigattion

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.tikaydev.glasseffect.core.designsystem.navigation.HomeRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.feature.home.detail.ImageDetailsScreen
import com.tikaydev.glasseffect.feature.home.images.ImageListRoute

fun EntryProviderScope<Route>.homeFlow(
    onBack: () -> Unit,
    onImageClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {

    entry<HomeRoute.ImageListRoute> {
        ImageListRoute(
            onClick = onImageClick,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = LocalNavAnimatedContentScope.current
        )
    }

    entry<HomeRoute.ImageDetailsRoute> {
        ImageDetailsScreen(
            imageIndex = it.imageId,
            onBack = onBack,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = LocalNavAnimatedContentScope.current,
        )
    }
}
