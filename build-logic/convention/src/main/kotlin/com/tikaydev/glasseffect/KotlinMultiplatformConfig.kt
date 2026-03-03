package com.tikaydev.glasseffect

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    jvmToolchain(17)
    jvm("desktop")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
            baseName = path.substring(1).replace(':', '-')
        }
    }
    applyDefaultHierarchyTemplate()
    sourceSets.apply {
        val desktopMain = getByName("desktopMain")
        commonMain {
            dependencies {
                api(libs.findLibrary("coroutines.core").get())
                api(libs.findLibrary("koin.core").get())
                api(libs.findLibrary("koin.compose").get())
                api(libs.findLibrary("koin.annotations").get())
            }
        }
        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin.android").get())
            }
        }
        desktopMain.dependencies {
            implementation(libs.findLibrary("coroutines.swing").get())
        }
    }
    compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
}