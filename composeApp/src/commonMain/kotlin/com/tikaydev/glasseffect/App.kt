package com.tikaydev.glasseffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.tikaydev.glasseffect.core.designsystem.provider.LocalHazeStateProvider
import com.tikaydev.glasseffect.core.designsystem.theme.AppTheme
import com.tikaydev.glasseffect.navigation.RootNavGraph
import com.tikaydev.glasseffect.navigation.component.BottomBar
import com.tikaydev.glasseffect.navigation.component.NavRail
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    viewModel: MainViewModel = koinViewModel()
) {
    val isDarkMode by viewModel.isDarkMode
    val appState: AppState = rememberAppState()
    val hazeState = remember { HazeState(true) }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isLargeScreen = windowSizeClass.isWidthAtLeastBreakpoint(
        WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    AppContent(
        isDarkMode = isDarkMode,
        selectedTabIndex = selectedTabIndex,
        appState = appState,
        hazeState = hazeState,
        isLargeScreen = isLargeScreen,
        onThemeToggle = viewModel::toggleTheme,
        onTabSelected = { tabIndex ->
            selectedTabIndex = tabIndex
        }
    )
}

@Composable
fun AppContent(
    isDarkMode: Boolean,
    selectedTabIndex: Int,
    appState: AppState,
    hazeState: HazeState,
    isLargeScreen: Boolean,
    onThemeToggle: () -> Unit,
    onTabSelected: (Int) -> Unit,
) {

    CompositionLocalProvider(
        LocalHazeStateProvider provides hazeState,
    ) {
        AppTheme(
            isDarkTheme = isDarkMode,
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Content is now always full screen, preventing resize jank during navigation
                    RootNavGraph(
                        appState = appState,
                        isLargeScreen = isLargeScreen,
                        onThemeToggle = onThemeToggle,
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(hazeState)
                    )

                    if (isLargeScreen) {
                        NavRail(
                            appState = appState,
                            hazeState = hazeState,
                            selectedTabIndex = selectedTabIndex,
                            onDestinationSelected = {
                                onTabSelected(appState.topLevelDestinations.indexOf(it))
                                appState.navigateToTopLevelDestination(it)
                            },
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.CenterStart)
                        )
                    }

                    if (!isLargeScreen) {
                        BottomBar(
                            appState = appState,
                            hazeState = hazeState,
                            selectedTabIndex = selectedTabIndex,
                            onDestinationSelected = {
                                onTabSelected(appState.topLevelDestinations.indexOf(it))
                                appState.navigateToTopLevelDestination(it)
                            },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppDarkPreview() {
    AppContent(
        isDarkMode = true,
        selectedTabIndex = 2,
        isLargeScreen = false,
        appState = rememberAppState(),
        hazeState = remember { HazeState(true) },
        onThemeToggle = {},
        onTabSelected = {},
    )
}

@Preview
@Composable
fun AppLightPreview() {
    AppContent(
        isDarkMode = false,
        selectedTabIndex = 0,
        isLargeScreen = false,
        appState = rememberAppState(),
        hazeState = remember { HazeState(true) },
        onThemeToggle = {},
        onTabSelected = {},
    )
}
