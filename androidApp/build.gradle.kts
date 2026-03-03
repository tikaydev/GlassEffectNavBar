plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.glass.compose.multiplatform.library)
    alias(libs.plugins.koin.compiler)
}

kotlin {
    android {
        namespace = "com.tikaydev.glasseffect"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        defaultConfig {
            applicationId = "com.tikaydev.glasseffect"
            minSdk = libs.versions.android.minSdk.get().toInt()
            targetSdk = libs.versions.android.targetSdk.get().toInt()
            versionCode = libs.versions.versionCode.get().toInt()
            versionName = libs.versions.versionName.get()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        buildFeatures {
            compose = true
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}

dependencies {
    implementation(projects.composeApp)
    implementation(projects.core.designsystem)

    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.resources)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.backhandler)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.viewmodel.compose)
    implementation(libs.kotlinx.serialization)


    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.ksafe)
}
