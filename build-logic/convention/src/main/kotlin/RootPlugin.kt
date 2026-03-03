/*
 * Copyright (C) 2025 Homi. All rights reserved.
 * Homi Android Team <developers@homi>
 * Created by Alex Tenkorang
 */

import com.tikaydev.glasseffect.configureGraphTasks
import org.gradle.api.Plugin
import org.gradle.api.Project

class RootPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        require(target.path == ":")
        target.subprojects { configureGraphTasks() }
    }
}
