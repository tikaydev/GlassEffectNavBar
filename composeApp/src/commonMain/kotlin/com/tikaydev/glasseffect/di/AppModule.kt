package com.tikaydev.glasseffect.di

import com.tikaydev.glasseffect.MainViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val appModule = module {
    viewModel {
        MainViewModel()
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration.invoke(this)

    /**
     * Shared Modules
     */
    modules(
        appModule,
    )
}

fun iOsInitKoin() {
    initKoin { }
}
