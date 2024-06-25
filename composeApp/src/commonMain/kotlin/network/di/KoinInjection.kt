package network.di

import networkModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

object KoinInjector {
    val koinApp = startKoin {
        printLogger(Level.DEBUG)
        loadKoinModules(
            listOf(
                networkModule,
                repositoryModule,
            )
        )
    }

    val koin = koinApp.koin
}