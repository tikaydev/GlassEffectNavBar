package com.tikaydev.glasseffect.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.tikaydev.glasseffect.core.designsystem.navigation.HomeRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.MenuRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.ProfileRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.core.designsystem.navigation.SettingsRoute


enum class TopLevelDestination(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val route: Route
) {
    HOME(
        title = "Home",
        icon = Icons.Rounded.Home,
        color = Color(0xFFFA6FFF),
        route = HomeRoute.ImageListRoute
    ),
    MENU(
        title = "Menu",
        icon = Icons.Rounded.Menu,
        color = Color(0xFFFFA574),
        route = MenuRoute
    ),
    PROFILE(
        title = "Profile",
        icon = Icons.Rounded.Person,
        color = Color(0xFFADFF64),
        route = ProfileRoute
    ),
    SETTINGS(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        color = Color.Red,
        route = SettingsRoute
    )
}