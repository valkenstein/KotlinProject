package domain.usecase

import domain.base.FlowResultUseCase
import domain.mapper.InviteGenerationMapper
import domain.model.InviteCellDom
import network.repository.ProfileRepository

class GenerationInviteCodeUseCase  constructor(
    private val profileRepository: ProfileRepository,
    private val InviteGenerationMapper: InviteGenerationMapper,
) : FlowResultUseCase<InviteCellDom>() {
    override suspend fun retrieveData() =  InviteGenerationMapper.map(profileRepository::generationInviteCode)

}