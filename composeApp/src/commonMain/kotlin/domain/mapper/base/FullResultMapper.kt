package domain.mapper.base

import domain.model.ResultDom
import network.data.BaseResponse

abstract class FullResultMapper<Source, Target> : BaseResultMapper<Source, Target>() {
    protected abstract fun mapSuccessResult(src: BaseResponse<Source>): Target
    override suspend fun map(src: BaseResponse<Source>): ResultDom<Target> {
        return mapFullResult(src, ::mapSuccessResult)
    }
}