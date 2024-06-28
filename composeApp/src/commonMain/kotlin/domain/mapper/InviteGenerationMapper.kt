package domain.mapper

import domain.mapper.base.ResultMapper
import domain.model.InviteCellDom
import network.data.InviteResponse

class InviteGenerationMapper  constructor(
    private val InviteListMapper: InviteListMapper
) : ResultMapper<InviteResponse, InviteCellDom>() {
    override fun mapSuccessResult(src: InviteResponse?): InviteCellDom {
        return src?.let { it.inviteCode?.let { it1 -> InviteListMapper.mapInvite(it1) } }
            ?: InviteCellDom()
    }

}