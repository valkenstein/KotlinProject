package di

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

object KoinInjector {
    val koinApp = startKoin {
        printLogger(Level.DEBUG)
        loadKoinModules(
            listOf(
                networkModule,
                apiModule,
                repositoryModule,
                mvvmModule,
                useCaseModule,
                mapperModule,
            )
        )
    }

    val koin = koinApp.koin
}