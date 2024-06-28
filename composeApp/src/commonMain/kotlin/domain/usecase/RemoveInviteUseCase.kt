package domain.usecase

import domain.base.FlowResultWithParamsUseCase
import domain.mapper.Empty2Mapper
import network.repository.ProfileRepository

class RemoveInviteUseCase constructor(
    private val profileRepository: ProfileRepository,
    private val profileOrderMapper: Empty2Mapper,
) : FlowResultWithParamsUseCase<String, String>() {
    override suspend fun retrieveData(params: String)=  profileOrderMapper.map(profileRepository.removeInvite(params))

}