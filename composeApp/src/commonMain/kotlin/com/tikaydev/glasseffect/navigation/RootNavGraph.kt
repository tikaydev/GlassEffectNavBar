package com.tikaydev.glasseffect.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    shouldShowNavRail: Boolean,
    onThemeToggle: () -> Unit
) {
    SharedTransitionLayout {
        NavDisplay(
            backStack = appState.navBackStack,
            transitionSpec = {
                // In Navigation 3, the targetState is a Scene<T>.
                // For SinglePaneSceneStrategy, targetState.content is a List<NavEntry<T>>.
                // We use the first entry's key to determine the destination.
                val targetRoute = targetState.key
                val initialRoute = initialState.key

                // Check if either the entering or exiting route is a detail screen
                if (targetRoute is HomeRoute.ImageDetailsRoute || initialRoute is HomeRoute.ImageDetailsRoute) {
                    fadeIn(animationSpec = spring(stiffness = 300f)) +
                            scaleIn(
                                initialScale = 0.92f,
                                animationSpec = spring(stiffness = 300f)
                            ) togetherWith
                            fadeOut(animationSpec = spring(stiffness = 300f))
                } else {
                    // Transition between top-level tabs (Home, Menu, Profile, Settings)
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
                    shouldUseNavRail = shouldShowNavRail,
                    onBack = { appState.navBackStack.removeLastOrNull() },
                    onImageClick = { imageId ->
                        appState.navBackStack.add(HomeRoute.ImageDetailsRoute(imageId))
                    },
                    sharedTransitionScope = this@SharedTransitionLayout
                )

                menuEntry(
                    shouldUseNavRail = shouldShowNavRail
                )
                profileEntry(
                    shouldUseNavRail = shouldShowNavRail
                )
                settingsEntry(
                    onThemeToggle = onThemeToggle
                )
            },
            modifier = modifier,
        )
    }
}
