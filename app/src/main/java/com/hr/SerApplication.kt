package com.hr

import NETWORKING_MODULE
import REPOSITORY_MODULE
import VIEW_MODEL_MODULE
import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SerApplication)
            loadKoinModules(REQUIRED_MODULE)
        }
    }

    companion object {
        val REQUIRED_MODULE = listOf(
            NETWORKING_MODULE,
            REPOSITORY_MODULE,
            VIEW_MODEL_MODULE
        )
    }
}