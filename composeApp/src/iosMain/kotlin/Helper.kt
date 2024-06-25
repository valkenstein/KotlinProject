import network.di.KoinInjector
import network.di.repositoryModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(listOf(networkModule, repositoryModule))
    }
}