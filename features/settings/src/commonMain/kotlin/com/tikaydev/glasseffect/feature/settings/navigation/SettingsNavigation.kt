package com.tikaydev.glasseffect.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.core.designsystem.navigation.SettingsRoute
import com.tikaydev.glasseffect.feature.settings.SettingsScreen

fun EntryProviderScope<Route>.settingsEntry() {
    entry<SettingsRoute> {
        SettingsScreen()
    }
}
