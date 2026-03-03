import com.android.build.api.dsl.ApkSigningConfig
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.internal.extensions.stdlib.toDefaultLowerCase
import org.gradle.kotlin.dsl.register
import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.net.URLEncoder

plugins {
    alias(libs.plugins.glass.kotlin.multiplatform)
    alias(libs.plugins.glass.compose.multiplatform.library)
}

kotlin {
    androidLibrary {
        namespace = "com.tikaydev.glasseffect.composeapp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.withType<Framework>().configureEach {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption("bundleId", "com.tikaydev.glasseffect")
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // core
            implementation(projects.core.designsystem)

            // features
            implementation(projects.features.home)
            implementation(projects.features.menu)
            implementation(projects.features.profile)
            implementation(projects.features.settings)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.resources)
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.backhandler)
            implementation(libs.compose.ui.tooling.preview)

            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.viewmodel.compose)

            implementation(libs.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.kotlinx.serialization)

            implementation(libs.navigation)

            implementation(libs.coil)
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            implementation(libs.coil.svg)

            implementation(libs.haze.materials)
            implementation(libs.liquid)

            implementation(libs.compose.adaptive)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.preference)

            implementation(libs.koin.android)

            implementation(libs.permissions)

            implementation(libs.ktor.okhttp)
            implementation(libs.coil.okhttp)
            implementation(libs.compose.ui.tooling)
            implementation(libs.androidx.activity.compose)

        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation(libs.coroutines.swing)


            implementation(libs.ktor.okhttp)
            implementation(libs.coil.okhttp)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.darwin)

        }
    }
}


buildkonfig {
    packageName = libs.versions.packageName.get()

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "VERSION_NAME", libs.versions.versionName.get())
        buildConfigField(
            FieldSpec.Type.INT,
            "VERSION_CODE",
            libs.versions.versionCode.get().toString()
        )
    }

}

