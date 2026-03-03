package com.tikaydev.glasseffect.feature.profile.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.tikaydev.glasseffect.core.designsystem.navigation.ProfileRoute
import com.tikaydev.glasseffect.core.designsystem.navigation.Route
import com.tikaydev.glasseffect.feature.profile.ProfileScreen

fun EntryProviderScope<Route>.profileEntry(shouldUseNavRail: Boolean) {
    entry<ProfileRoute> {
        ProfileScreen(shouldUseNavRail = shouldUseNavRail)
    }
}
