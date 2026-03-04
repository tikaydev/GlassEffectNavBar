package com.tikaydev.glasseffect.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Help
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.PrivacyTip
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tikaydev.glasseffect.core.designsystem.provider.darkTheme
import com.tikaydev.glasseffect.core.designsystem.theme.AppTheme

@Composable
fun SettingsScreen(
    onThemeToggle: () -> Unit
) {
    // Read the theme state from CompositionLocal to ensure it updates reactively
    val isDarkMode = MaterialTheme.darkTheme.isDark


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp, top = 32.dp)
            )

            SettingsSection(title = "App Settings") {
                SettingsItem(
                    icon = Icons.Rounded.ColorLens,
                    title = "Dark Mode",
                    subtitle = if (isDarkMode) "Enabled" else "Disabled",
                    onClick = onThemeToggle,
                    trailingContent = {
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { onThemeToggle() }
                        )
                    }
                )
                ItemDivider()
                SettingsItem(
                    icon = Icons.Rounded.Notifications,
                    title = "Notifications",
                    subtitle = "Alerts, sounds"
                )
                ItemDivider()
                SettingsItem(
                    icon = Icons.Rounded.Language,
                    title = "Language",
                    subtitle = "English (US)"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            SettingsSection(title = "Privacy & Security") {
                SettingsItem(
                    icon = Icons.Rounded.PrivacyTip,
                    title = "Privacy Policy"
                )
                ItemDivider()
                SettingsItem(
                    icon = Icons.Rounded.Security,
                    title = "Security"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            SettingsSection(title = "About") {
                SettingsItem(
                    icon = Icons.AutoMirrored.Rounded.Help,
                    title = "Help & Support"
                )
                ItemDivider()
                SettingsItem(
                    icon = Icons.Rounded.Info,
                    title = "App Info",
                    subtitle = "Version 1.0.0"
                )
            }

            // Add extra space at the bottom for navigation bars
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun ItemDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 48.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
    )
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column{
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(16.dp)
            .padding(vertical = 8.dp)
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
        
        if (trailingContent != null) {
            trailingContent()
        } else {
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun SettingsScreenDarkPreview() {
    AppTheme(isDarkTheme = true){
        SettingsScreen {  }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun SettingsScreenLightPreview() {
    AppTheme{
        SettingsScreen {  }
    }
}