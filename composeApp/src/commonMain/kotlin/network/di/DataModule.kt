package network.di

import de.jensklingenberg.ktorfit.Ktorfit
import domain.BonusOperationUseCase
import network.api.NetworkApi
import network.repository.Repository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.mvvm.ContactListViewModel

internal val repositoryModule = module {
    factory { Repository(get()) }
    factory { BonusOperationUseCase(get()) }
    //viewModelOf(::ContactListViewModel)
    viewModel { ContactListViewModel(get()) }
    factory { get<Ktorfit>().create<NetworkApi>() }
}


