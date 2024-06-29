package org.example.project

import android.app.Application
import di.KoinInjector
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInjector.koinApp
//        startKoin {
//            //androidContext(this@MyApp)
//            printLogger(Level.DEBUG)
//            modules(listOf(networkModule, repositoryModule))
//        }
    }
}