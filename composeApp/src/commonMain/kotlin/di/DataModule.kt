package di

import de.jensklingenberg.ktorfit.Ktorfit
import domain.mapper.Empty2Mapper
import domain.mapper.HomeMapper
import domain.mapper.InviteGenerationMapper
import domain.mapper.InviteListMapper
import domain.usecase.BonusOperationUseCase
import domain.mapper.ProfileAccumulativeInfoMapper
import domain.mapper.StatusAccumulativeMapper
import domain.usecase.AccumulativeBrandsUseCase
import domain.usecase.AccumulativeStatusUseCase
import domain.usecase.GenerationInviteCodeUseCase
import domain.usecase.GetInviteListUseCase
import domain.usecase.InformationInviteUseCase
import domain.usecase.RemoveInviteUseCase
import domain.usecase.RestoreInviteCodeUseCase
import network.api.IApiHomeService
import network.api.IAuthorizationApiService
import network.api.NetworkApi
import network.repository.ContentHomeRepository
import network.repository.ProfileRepository
import network.repository.Repository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.mvvm.ContactListViewModel
import presentation.mvvm.InviteViewModel
import presentation.mvvm.LevelInformationViewModel

internal val apiModule = module {
    factory { get<Ktorfit>().create<NetworkApi>() }
    factory { get<Ktorfit>().create<IAuthorizationApiService>() }
    factory { get<Ktorfit>().create<IApiHomeService>() }

}

internal val repositoryModule = module {
    factory { Repository(get()) }
    factory { ProfileRepository(get()) }
    factory { ContentHomeRepository(get()) }
}

internal val useCaseModule = module {
    factory { BonusOperationUseCase(get(), get()) }
    factory { GenerationInviteCodeUseCase(get(), get()) }
    factory { GetInviteListUseCase(get(), get()) }
    factory { InformationInviteUseCase(get(), get()) }
    factory { RemoveInviteUseCase(get(), get()) }
    factory { RestoreInviteCodeUseCase(get(), get()) }
    factory { AccumulativeBrandsUseCase(get(), get()) }
    factory { AccumulativeStatusUseCase(get(), get()) }
}
internal val mapperModule = module {
    factory { Empty2Mapper() }
    factory { InviteGenerationMapper(get()) }
    factory { InviteListMapper() }
    factory { ProfileAccumulativeInfoMapper() }
    factory { HomeMapper() }
    factory { StatusAccumulativeMapper(get()) }
}

internal val mvvmModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { InviteViewModel(get(), get(), get(), get(), get()) }
    viewModel { LevelInformationViewModel(get(), get()) }
}




