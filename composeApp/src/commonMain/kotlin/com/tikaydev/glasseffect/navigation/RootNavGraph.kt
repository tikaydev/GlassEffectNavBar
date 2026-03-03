package com.tikaydev.glasseffect.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.tikaydev.glasseffect.AppState
import com.tikaydev.glasseffect.core.designsystem.navigation.HomeRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.feature.home.navigattion.homeFlow
import com.tikaydev.glasseffect.feature.menu.navigation.menuEntry
import com.tikaydev.glasseffect.feature.profile.navigation.profileEntry
import com.tikaydev.glasseffect.feature.settings.navigation.settingsEntry

@Composable
fun RootNavGraph(
    appState: AppState,
    shouldShowNavRail: Boolean,
) {
    SharedTransitionLayout {
        NavDisplay(
            backStack = appState.navBackStack,
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

                menuEntry()
                profileEntry()
                settingsEntry()
            }
        )
    }
}
