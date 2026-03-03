package com.tikaydev.glasseffect

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    extension: LibraryExtension
) = extension.apply {
    val moduleName = path
        .split(":")
        .drop(2)
        .joinToString(".")
    namespace = if (moduleName.isNotEmpty()) {
        "com.tikaydev.glasseffect.$moduleName"
    } else {
        "com.tikaydev.glasseffect"
    }
    compileSdk = libs.findVersion("android.compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}