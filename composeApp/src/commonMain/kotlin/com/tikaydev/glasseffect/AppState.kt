package com.tikaydev.glasseffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.tikaydev.glasseffect.core.designsystem.navigation.HomeRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.MenuRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.ProfileRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.core.designsystem.navigation.SettingsRoute
import com.tikaydev.glasseffect.navigation.TopLevelDestination

@Composable
fun rememberAppState(
    navBackStack: SnapshotStateList<Route> = remember { mutableStateListOf(HomeRoute.ImageListRoute) }
): AppState {
    return remember(navBackStack) {
        AppState(
            navBackStack = navBackStack,
        )
    }
}

@Stable
class AppState(
    val navBackStack: SnapshotStateList<Route>,
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    private val bottomBarRoutes = TopLevelDestination.entries.map { it.route }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                navBackStack.lastOrNull() == topLevelDestination.route
            }
        }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        return navBackStack.lastOrNull() in bottomBarRoutes
    }

    fun isTopLevelRoute(route: Route?): Boolean {
        return route in bottomBarRoutes
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val targetRoute = when (topLevelDestination) {
            TopLevelDestination.HOME -> HomeRoute.ImageListRoute
            TopLevelDestination.MENU -> MenuRoute
            TopLevelDestination.PROFILE -> ProfileRoute
            TopLevelDestination.SETTINGS -> SettingsRoute
        }

        if (navBackStack.lastOrNull() == targetRoute) return

        if (navBackStack.lastOrNull() != HomeRoute.ImageListRoute) {
            navBackStack.removeLastOrNull()
        }
        navBackStack.add(targetRoute)
    }
}
