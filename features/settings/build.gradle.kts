
plugins {
    alias(libs.plugins.glass.compose.multiplatform.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.tikaydev.glasseffect.features.settings"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

buildkonfig {
    packageName = "com.tikaydev.glasseffect.features.settings"
    defaultConfigs {}
}