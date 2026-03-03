package com.tikaydev.glasseffect

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.tikaydev.glasseffect.di.initKoin
//import com.tikaydev.glasseffect.di.initKoin
import org.koin.plugin.module.dsl.startKoin

fun main() {
    initKoin()
    application {
        Window(
            title = "Glass Effect",
            onCloseRequest = ::exitApplication,
            state = WindowState(size = DpSize(1000.dp, 900.dp)),
        ) {
            App()
        }
    }

}