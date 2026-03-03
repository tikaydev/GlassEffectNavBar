import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.tikaydev.glasseffect.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("pvault.kotlin.multiplatform").get().get().pluginId)
            apply(libs.findPlugin("pvault.compose.multiplatform.library").get().get().pluginId)
            apply(libs.findPlugin("android.library").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(project(":core:designsystem"))

                        implementation(libs.findLibrary("compose.runtime").get())
                        implementation(libs.findLibrary("compose.foundation").get())
                        implementation(libs.findLibrary("compose.material3").get())
                        implementation(libs.findLibrary("compose.material.icons.extended").get())
                        implementation(libs.findLibrary("compose.resources").get())
                        implementation(libs.findLibrary("compose.ui.tooling.preview").get())
                        implementation(libs.findLibrary("compose.ui.backhandler").get())

                        implementation(libs.findLibrary("coroutines.core").get())

                        implementation(libs.findLibrary("navigation.ui").get())
                        implementation(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                        implementation(libs.findLibrary("viewmodel.compose").get())
                        implementation(libs.findLibrary("lifecycle.viewmodel.navigation3").get())

                        implementation(libs.findLibrary("koin.core").get())
                        implementation(libs.findLibrary("koin.compose").get())
                        implementation(libs.findLibrary("koin.annotations").get())
                        implementation(libs.findLibrary("koin.compose.navigation").get())
                        implementation(libs.findLibrary("koin.compose.viewmodel").get())

                        implementation(libs.findLibrary("kotlinx.serialization").get())


                        implementation(libs.findLibrary("coil.svg").get())
                        implementation(libs.findLibrary("coil.ktor").get())
                        implementation(libs.findLibrary("coil.compose").get())
                        implementation(libs.findLibrary("haze.materials").get())
                        implementation(libs.findLibrary("liquid").get())

                    }
                }
            }
        }

        pluginManager.withPlugin("buildconfig") {
            extensions.configure<BuildKonfigExtension>() {
                defaultConfigs {

                }
                packageName = namespace
            }
        }
    }
}
