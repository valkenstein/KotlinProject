package domain.usecase

import domain.base.FlowResultWithDoubleParamsUseCase
import domain.mapper.InviteListMapper
import domain.model.InviteListDom
import network.repository.ProfileRepository

class GetInviteListUseCase constructor(
    private val profileRepository: ProfileRepository,
    private val InviteListMapper: InviteListMapper,
) : FlowResultWithDoubleParamsUseCase<Int, String, InviteListDom>() {
    override suspend fun retrieveData(
        params1: Int,
        params2: String
    ) = InviteListMapper.map {
        profileRepository.getInviteList(params1, params2)
    }
}