package com.tikaydev.glasseffect.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.tikaydev.glasseffect.AppState
import com.tikaydev.glasseffect.core.designsystem.navigation.HomeRoute
import com.tikaydev.glasseffect.feature.home.navigattion.homeFlow
import com.tikaydev.glasseffect.feature.menu.navigation.menuEntry
import com.tikaydev.glasseffect.feature.profile.navigation.profileEntry
import com.tikaydev.glasseffect.feature.settings.navigation.settingsEntry

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    appState: AppState,
    isLargeScreen: Boolean,
    onThemeToggle: () -> Unit
) {
    // Add padding to the content area if the NavRail is visible to prevent overlap
    val contentPadding = if (isLargeScreen && appState.shouldShowBottomBar()) {
        Modifier.padding(start = 96.dp) // Width of NavRail (80dp) + its horizontal padding
    } else {
        Modifier
    }

    SharedTransitionLayout {
        NavDisplay(
            backStack = appState.navBackStack,
            transitionSpec = {
                val targetRoute = targetState.key
                val initialRoute = initialState.key

                if (targetRoute is HomeRoute.ImageDetailsRoute || initialRoute is HomeRoute.ImageDetailsRoute) {
                    fadeIn(animationSpec = spring(stiffness = 300f)) +
                            scaleIn(
                                initialScale = 0.92f,
                                animationSpec = spring(stiffness = 300f)
                            ) togetherWith
                            fadeOut(animationSpec = spring(stiffness = 300f))
                } else {
                    fadeIn(animationSpec = spring(stiffness = 300f)) +
                            scaleIn(
                                initialScale = 0.96f,
                                animationSpec = spring(stiffness = 300f)
                            ) togetherWith
                            fadeOut(animationSpec = spring(stiffness = 300f)) +
                            scaleOut(targetScale = 0.96f, animationSpec = spring(stiffness = 300f))
                }
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                homeFlow(
                    isLargeScreen = isLargeScreen,
                    onBack = { appState.navBackStack.removeLastOrNull() },
                    onImageClick = { imageId ->
                        appState.navBackStack.add(HomeRoute.ImageDetailsRoute(imageId))
                    },
                    sharedTransitionScope = this@SharedTransitionLayout
                )

                menuEntry(isLargeScreen = isLargeScreen)
                profileEntry(isLargeScreen = isLargeScreen)
                settingsEntry(
                    onThemeToggle = onThemeToggle
                )
            },
            modifier = modifier.then(contentPadding),
        )
    }
}
