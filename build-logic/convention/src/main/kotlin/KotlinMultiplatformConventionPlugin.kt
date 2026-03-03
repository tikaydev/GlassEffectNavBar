import com.android.build.api.dsl.LibraryExtension
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.tikaydev.glasseffect.configureKotlinAndroid
import com.tikaydev.glasseffect.configureKotlinMultiplatform
import com.tikaydev.glasseffect.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-multiplatform").get().get().pluginId)
            apply(libs.findPlugin("android-library").get().get().pluginId)
            apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
            apply(libs.findPlugin("koin-compiler").get().get().pluginId)
            apply(libs.findPlugin("buildkonfig").get().get().pluginId)
            apply(libs.findPlugin("ksp").get().get().pluginId)
        }
        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this)
        }

        // 2️⃣ Configure Android ONLY after plugin is applied
        pluginManager.withPlugin("com.android.library") {
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }

        pluginManager.withPlugin("buildconfig") {
            extensions.configure<BuildKonfigExtension>() {
                packageName = libs.findVersion("packageName").get().toString()
                defaultConfigs {

                }
            }

        }
    }


}