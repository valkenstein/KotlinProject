package network.di

import de.jensklingenberg.ktorfit.Ktorfit
import domain.usecase.BonusOperationUseCase
import domain.mapper.ProfileAccumulativeInfoMapper
import network.api.NetworkApi
import network.repository.Repository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.mvvm.ContactListViewModel

internal val repositoryModule = module {
    factory { Repository(get()) }
    factory { ProfileAccumulativeInfoMapper() }
    factory { BonusOperationUseCase(get(),get()) }
    //viewModelOf(::ContactListViewModel)
    viewModel { ContactListViewModel(get()) }
    factory { get<Ktorfit>().create<NetworkApi>() }
}


