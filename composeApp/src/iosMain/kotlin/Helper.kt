import network.di.KoinInjector
import network.di.repositoryModule
import org.koin.core.context.startKoin

fun initKoin() {
    KoinInjector.koinApp
//    startKoin {
//        modules(listOf(networkModule, repositoryModule))
//    }
}