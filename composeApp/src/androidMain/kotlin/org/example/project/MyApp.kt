package org.example.project

import android.app.Application
import network.di.repositoryModule
import networkModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //androidContext(this@MyApp)
            printLogger(Level.DEBUG)
            modules(listOf(networkModule, repositoryModule))
        }
    }
}