package com.tikaydev.glasseffect

import android.app.Application
import com.tikaydev.glasseffect.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class GlassEffectApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appDeclaration = {
                androidLogger()
                androidContext(this@GlassEffectApplication)
            }
        )
    }
}