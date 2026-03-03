plugins {
    `kotlin-dsl`
}

group = "com.tikaydev.glasseffect.buildlogic"

dependencies {
    compileOnly(libs.plugins.android.application.toDep())
    compileOnly(libs.plugins.android.library.toDep())
    compileOnly(libs.plugins.jetbrains.compose.toDep())
    compileOnly(libs.plugins.kotlin.multiplatform.toDep())
    compileOnly(libs.plugins.compose.compiler.toDep())
    compileOnly(libs.plugins.kotlin.serialization.toDep())
    implementation(libs.plugins.buildkonfig.toDep())
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

fun Provider<PluginDependency>.toDep() = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = libs.plugins.pvault.kotlin.multiplatform.get().pluginId
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform"){
            id = libs.plugins.pvault.compose.multiplatform.library.get().pluginId
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("composeMultiplatformFeature"){
            id = libs.plugins.pvault.compose.multiplatform.feature.get().pluginId
            implementationClass = "ComposeMultiplatformFeatureConventionPlugin"
        }
        register("iosVersionUpdate") {
            id = libs.plugins.pvault.ios.versionUpdate.get().pluginId
            implementationClass = "com.tikaydev.glasseffect.IosVersionUpdatePlugin"
        }
        register("root") {
            id = libs.plugins.pvault.root.get().pluginId
            implementationClass = "RootPlugin"
        }
    }
}
