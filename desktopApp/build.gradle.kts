import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.koin.compiler)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


kotlin {
    jvm()
    sourceSets {
        jvmMain {
            dependencies {
                implementation(projects.composeApp)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose {
    desktop.application {
        mainClass = "com.tikaydev.glasseffect.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = libs.versions.packageName.get()
            packageVersion = libs.versions.versionName.get()

            includeAllModules = true

            windows {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/icon.ico"))
            }
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/icon.png"))
            }
        }

        buildTypes {
            release {
                proguard {
                    obfuscate.set(true)
                    optimize.set(false)
                    configurationFiles.from(project.file("proguard-desktop.pro"))
                }
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.tikaydev.glasseffect.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.tikaydev.glasseffect"
            packageVersion = "1.0.0"
        }
    }
}

kotlin.sourceSets.commonMain{
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}