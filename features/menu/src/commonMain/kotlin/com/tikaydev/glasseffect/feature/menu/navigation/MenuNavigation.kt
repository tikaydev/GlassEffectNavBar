package com.tikaydev.glasseffect.feature.menu.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.tikaydev.glasseffect.core.designsystem.navigation.MenuRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.feature.menu.MenuScreen

fun EntryProviderScope<Route>.menuEntry() {
    entry<MenuRoute> {
        MenuScreen()
    }
}
