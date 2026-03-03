package com.tikaydev.glasseffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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


@Composable
fun App() {
    val appState: AppState = rememberAppState()
    val hazeState = remember { HazeState(true) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val shouldShowNavRail = windowSizeClass.isWidthAtLeastBreakpoint(
        WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND
    )

    CompositionLocalProvider(
        LocalHazeStateProvider provides hazeState,
    ) {
        AppTheme(
            darkTheme = true,
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { paddingValues ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    if (shouldShowNavRail) {
                        NavRail(
                            appState = appState,
                            hazeState = hazeState,
                            selectedTabIndex = selectedTabIndex,
                            onDestinationSelected = {
                                selectedTabIndex = appState.topLevelDestinations.indexOf(it)
                                appState.navigateToTopLevelDestination(it)
                            },
                            modifier = Modifier.fillMaxHeight()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        RootNavGraph(
                            appState = appState,
                            shouldShowNavRail = shouldShowNavRail,
                        )

                        if (!shouldShowNavRail) {
                            BottomBar(
                                appState = appState,
                                hazeState = hazeState,
                                selectedTabIndex = selectedTabIndex,
                                onDestinationSelected = {
                                    selectedTabIndex = appState.topLevelDestinations.indexOf(it)
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
}

@Preview
@Composable
fun AppPreview() {
    App()
}