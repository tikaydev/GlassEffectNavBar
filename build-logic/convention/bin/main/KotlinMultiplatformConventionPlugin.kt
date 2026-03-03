import com.tikaydev.glasseffect.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin.multiplatform").get().get().pluginId)
            apply(libs.findPlugin("android.library").get().get().pluginId)
        }

        // Configure Kotlin Multiplatform extension
        extensions.configure<KotlinMultiplatformExtension> {
            // Configure iOS targets
            listOf(
                iosArm64(), // for ios devices
                iosSimulatorArm64(), // for ios simulators in Apple silicon Mac computer
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = path.substring(1).replace(':', '-')
                }
            }

//            jvm()
            //remove expect actual warning
            compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }
}
