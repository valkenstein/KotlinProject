package domain.usecase

import domain.base.FlowResultWithParamsUseCase
import domain.mapper.InviteGenerationMapper
import domain.model.InviteCellDom
import network.repository.ProfileRepository

class RestoreInviteCodeUseCase constructor(
    private val profileRepository: ProfileRepository,
    private val InviteGenerationMapper: InviteGenerationMapper,
) : FlowResultWithParamsUseCase<String, InviteCellDom>() {
    override suspend fun retrieveData(params: String) =  InviteGenerationMapper.map{profileRepository.restoreInviteCode(params)}

}