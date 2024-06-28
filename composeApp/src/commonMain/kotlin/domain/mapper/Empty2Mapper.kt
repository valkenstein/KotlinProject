package domain.mapper

import domain.mapper.base.FullResultMapper
import network.data.BaseResponse
import network.data.EmptyResponse

class Empty2Mapper constructor(
) : FullResultMapper<EmptyResponse, String>() {
    override fun mapSuccessResult(src: BaseResponse<EmptyResponse>): String =
        src.data?.text ?: "" + src.message

}