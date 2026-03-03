
plugins {
    alias(libs.plugins.pvault.kotlin.multiplatform)
    alias(libs.plugins.pvault.compose.multiplatform.library)
    alias(libs.plugins.kotlin.serialization)  // for navigation routes
}


kotlin {
    androidLibrary {
        namespace = "com.tikaydev.glasseffect.core.designsystem"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        androidResources.enable = true
    }



    sourceSets {
        androidMain.dependencies {
            // Tooling support - runtime only but transitive to consumer modules
            runtimeOnly(libs.compose.ui.tooling)
        }
        commonMain.dependencies {
            api(libs.compose.material3)
            api(libs.compose.material.icons.extended)
            api(libs.compose.resources)
            api(libs.compose.ui.tooling.preview)

            api(libs.materialKolor)
            api(libs.navigation.ui)
            api(libs.lifecycle.viewmodel.navigation3)
            api(libs.koin.compose.navigation)
            api(libs.koin.compose.navigation)

            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            implementation(libs.coil.svg)

            implementation(libs.compose.adaptive)
            implementation(libs.liquid)
            implementation(libs.haze.materials)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.tikaydev.glasseffect.core.designsystem.resources"
    generateResClass = always
}

buildkonfig {
    packageName = "com.tikaydev.glasseffect.core.designsystem"

    defaultConfigs {

    }
}