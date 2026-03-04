package com.tikaydev.glasseffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tikaydev.glasseffect.core.designsystem.provider.LocalHazeStateProvider
import com.tikaydev.glasseffect.core.designsystem.provider.screenSize
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
    val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
    val appState: AppState = rememberAppState()
    val hazeState = remember { HazeState(true) }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    AppTheme(
        isDarkTheme = isDarkMode,
    ) {
        AppContent(
            selectedTabIndex = selectedTabIndex,
            appState = appState,
            hazeState = hazeState,
            onThemeToggle = viewModel::toggleTheme,
            onTabSelected = { tabIndex ->
                selectedTabIndex = tabIndex
            }
        )
    }
}

@Composable
fun AppContent(
    selectedTabIndex: Int,
    appState: AppState,
    hazeState: HazeState,
    onThemeToggle: () -> Unit,
    onTabSelected: (Int) -> Unit,
) {

    CompositionLocalProvider(
        LocalHazeStateProvider provides hazeState,
    ) {
        val isLargeScreen = MaterialTheme.screenSize.isLargeScreen
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

@Preview(name = "Phone")
@Preview(
    name = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
    showSystemUi = true,
)
@Preview(
    name = "Tablet - Landscape",
    device = "spec:width=1280dp,height=1200dp,dpi=240,orientation=portrait",
    showSystemUi = true,
)
@Composable
fun AppDarkPreview() {
    AppTheme (isDarkTheme = true){
        AppContent(
            selectedTabIndex = 2,
            appState = rememberAppState(),
            hazeState = remember { HazeState(true) },
            onThemeToggle = {},
            onTabSelected = {},
        )
    }
}

@Preview("Phone")
@Preview(
    name = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
    showSystemUi = true,
)
@Preview(
    name = "Tablet - Landscape",
    device = "spec:width=1280dp,height=1200dp,dpi=240,orientation=portrait",
    showSystemUi = true,
)
@Composable
fun AppLightPreview() {
    AppTheme {
        AppContent(
            selectedTabIndex = 0,
            appState = rememberAppState(),
            hazeState = remember { HazeState(true) },
            onThemeToggle = {},
            onTabSelected = {},
        )
    }
}
