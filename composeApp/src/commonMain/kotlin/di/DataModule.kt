package di

import de.jensklingenberg.ktorfit.Ktorfit
import domain.mapper.Empty2Mapper
import domain.mapper.InviteGenerationMapper
import domain.mapper.InviteListMapper
import domain.usecase.BonusOperationUseCase
import domain.mapper.ProfileAccumulativeInfoMapper
import domain.usecase.GenerationInviteCodeUseCase
import domain.usecase.GetInviteListUseCase
import domain.usecase.InformationInviteUseCase
import domain.usecase.RemoveInviteUseCase
import domain.usecase.RestoreInviteCodeUseCase
import network.api.IAuthorizationApiService
import network.api.NetworkApi
import network.repository.ProfileRepository
import network.repository.Repository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.mvvm.ContactListViewModel
import presentation.mvvm.InviteViewModel

internal val apiModule = module {
    factory { get<Ktorfit>().create<NetworkApi>() }
    factory { get<Ktorfit>().create<IAuthorizationApiService>() }
}

internal val repositoryModule = module {
    factory { Repository(get()) }
    factory { ProfileRepository(get()) }
}

internal val useCaseModule = module {
    factory { BonusOperationUseCase(get(), get()) }
    factory { GenerationInviteCodeUseCase(get(), get()) }
    factory { GetInviteListUseCase(get(), get()) }
    factory { InformationInviteUseCase(get(), get()) }
    factory { RemoveInviteUseCase(get(), get()) }
    factory { RestoreInviteCodeUseCase(get(), get()) }
}

internal val mvvmModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { InviteViewModel(get(), get(), get(), get(), get()) }
}

internal val mapperModule = module {
    factory { Empty2Mapper() }
    factory { InviteGenerationMapper(get()) }
    factory { InviteListMapper() }
    factory { ProfileAccumulativeInfoMapper() }
}


