package domain.mapper.base

import domain.model.ResultDom
import network.data.BaseResponse

abstract class ResultMapper<Source, Target> : BaseResultMapper<Source, Target>() {
    protected abstract fun mapSuccessResult(src: Source?): Target
    override suspend fun map(src: BaseResponse<Source>): ResultDom<Target> {
        return fullResultExceptionToRuleMapper(src, ::mapSuccessResult)
    }
}