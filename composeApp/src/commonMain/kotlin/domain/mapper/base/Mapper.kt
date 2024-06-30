package domain.mapper.base

import network.data.BaseResponse
import network.data.InviteResponse

interface Mapper<Source, Target> {
   suspend fun map(src: suspend () -> Source): Target
}

interface ReverseMapper<Source, Target> : Mapper<Source, Target> {
    fun reverseMap(target: Target): Source
}